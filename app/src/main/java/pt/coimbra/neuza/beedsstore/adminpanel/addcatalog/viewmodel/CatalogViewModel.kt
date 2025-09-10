package pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.coimbra.neuza.beedsstore.Injection
import pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.data.BeedRepository
import pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.model.Beed

class CatalogViewModel: ViewModel() {
    private val beedRepo = BeedRepository(Injection.instance(), Injection.storageInstance())

    sealed interface UiState {
        data object Idle : UiState
        data object Loading : UiState
        data object Sucess : UiState
        data class Error(val message:String) : UiState

    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    fun onPickAndSave(imageUri : Uri, beed : Beed){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                beedRepo.createBeedWithImage(imageUri = imageUri, beed = beed)
                _uiState.value = UiState.Sucess
            }catch (e : Exception){
                _uiState.value = UiState.Error(e.message ?: "Upload Failed")
            }
        }
    }
}