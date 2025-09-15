package pt.coimbra.neuza.beedsstore.authentication.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import pt.coimbra.neuza.beedsstore.authentication.model.User
import pt.coimbra.neuza.beedsstore.authentication.model.UserType


class UserRepository(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun signUp(email : String , password : String, username : String) : pt.coimbra.neuza.beedsstore.authentication.model.Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email,password).await()
            val user = User(username = username, email = email, "admin")
            saveUsersToFirestore(user)
            pt.coimbra.neuza.beedsstore.authentication.model.Result.Success(true)
        } catch (e: Exception) {
            Log.e("UserRepository", "Signup failed", e)
            pt.coimbra.neuza.beedsstore.authentication.model.Result.Error(e)
        }

    private suspend fun saveUsersToFirestore(user : User){
        firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun login(email : String, password: String) : Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        }catch (e : Exception){
            Result.Error(e)
        }

    suspend fun getCurrentUser(): Result<User> =
        try {
            val uid = auth.currentUser?.email
            if(uid != null){
                val userDocument = firestore.collection("users").document(uid).get().await()
                val user = userDocument.toObject(User::class.java)
                if(user != null ) {
                    Log.d("user2","$uid")
                    Result.Success(user)
                }else {
                    Result.Error(Exception("User data not found"))
                }
            }else{
                Result.Error(Exception("User not authenticated"))
            }
        }catch (e: Exception){
            Result.Error(e)
        }
    suspend fun getUserType(email: String): UserType {
        return try {
            val doc = firestore.collection("users").document(email).get().await()
            when(doc.getString("userType")) {
                "admin" -> UserType.Admin
                "general" -> UserType.General
                else -> UserType.General // default role
            }
        } catch (e: Exception) {
            UserType.Error(e)
        }
    }
//    suspend fun getUserType(email : String): String? {
//        val doc = firestore.collection("users").document(email).get().await()
//        return doc.getString("userType")
//    }




}