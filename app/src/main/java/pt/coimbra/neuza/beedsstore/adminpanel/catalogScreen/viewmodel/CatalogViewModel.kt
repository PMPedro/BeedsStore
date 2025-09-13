package pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.coimbra.neuza.beedsstore.Injection
import pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.data.BeedRepositoryCat
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed
import pt.coimbra.neuza.beedsstore.authentication.model.Result

class CatalogViewModel () : ViewModel () {
    private val _beeds = MutableLiveData<List<Beed>>(emptyList())
    private val beedRepository = BeedRepositoryCat(Injection.instance(), Injection.storageInstance())

    val beeds : LiveData<List<Beed>> = _beeds

    init{
        viewModelScope.launch {
            when(val result = beedRepository.getBeedsFromFirestore()){
                is Result.Success -> {
                    _beeds.value = result.data
                }
                is Result.Error -> {
                    Log.e("Error", "Error fetching data!")
                }
            }
        }
    }

    fun deleteBeed(beed : Beed){
        val prevList = _beeds.value ?: emptyList()
        _beeds.value = prevList.filter { it.title != beed.title }

        viewModelScope.launch {
            try {
                beedRepository.deleteBeedImage(beed)
                beedRepository.deleteBeedFirestore(beed)
            }catch (e : Exception){
                Log.e("Firebase", "Failed to remove the data from database!", e)
                _beeds.value = prevList
            }
        }
    }
}