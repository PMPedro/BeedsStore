package pt.coimbra.neuza.beedsstore.adminpanel.catalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.coimbra.neuza.beedsstore.Injection
import pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.data.BeedRepository
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed
import pt.coimbra.neuza.beedsstore.authentication.model.Result

class CatalogViewModel () : ViewModel () {
    private val _beeds = MutableLiveData<List<Beed>>(emptyList())
    private val beedRepository = BeedRepository(Injection.instance(), Injection.storageInstance())
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
}