package pt.coimbra.neuza.beedsstore.userpanel.catalog.viewModel

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

class catalogViewModelUser : ViewModel() {
    private val _beeds = MutableLiveData<List<Beed>>(emptyList())

    val beeds : LiveData<List<Beed>> = _beeds
    private val beedRepository = BeedRepositoryCat(Injection.instance(), Injection.storageInstance())

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

}