package pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.data

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.model.Beed
import pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.model.beedList
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import java.util.UUID

class BeedRepository( private val firestore: FirebaseFirestore, private val storage: FirebaseStorage) {

    suspend fun saveBeedToFirestore(beed : Beed){
        firestore.collection("beeds").document(beed.title).set(beed).await()
    }

    suspend fun getBeedsFromFirestore() : Result<Boolean> =
        try{
            val snapshot = firestore.collection("beeds").get().await()
            beedList.clear()
            for(document in snapshot){
                val beed = document.toObject(Beed::class.java)
                beedList.add(beed)
            }
            Result.Success(true)
        }catch (e: Exception){
            Result.Error(e)
        }

    suspend fun uploadImage(uri: Uri): String {
        val ref = storage.reference.child("beeds/${UUID.randomUUID()}.jpg")
        ref.putFile(uri).await()
        return ref.downloadUrl.await().toString()
    }

    suspend fun createBeedWithImage(imageUri: Uri, beed: Beed){
        val url = uploadImage(imageUri)
        saveBeedToFirestore(beed.copy(imageURL = url))
    }
}
