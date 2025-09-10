package pt.coimbra.neuza.beedsstore.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import pt.coimbra.neuza.beedsstore.Injection
import pt.coimbra.neuza.beedsstore.authentication.data.UserRepository
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import pt.coimbra.neuza.beedsstore.authentication.model.UserType

class AuthViewModel : ViewModel() {
    private val userRepository : UserRepository

    init{
        userRepository = UserRepository(FirebaseAuth.getInstance(), Injection.instance())
    }
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult : LiveData<Result<Boolean>> get() = _authResult

    val _isAdmin = MutableLiveData<UserType>()
    val isAdmin : LiveData<UserType> get() = _isAdmin

    fun signUp(email : String, password: String, username : String){
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, username)
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }

    fun userType(email : String){
        viewModelScope.launch {
            _isAdmin.value = userRepository.getUserType(email)
        }
    }

//    suspend fun checkIfAdmin(email: String): Boolean{
//            val usertype = userRepository.getUserType(email)
//            return  (usertype == "admin")
//    }

}

