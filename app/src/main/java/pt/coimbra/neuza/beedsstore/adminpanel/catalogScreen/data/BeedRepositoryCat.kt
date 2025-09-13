package pt.coimbra.neuza.beedsstore.adminpanel.catalogScreen.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed
import pt.coimbra.neuza.beedsstore.authentication.model.Result

class BeedRepositoryCat( private val firestore: FirebaseFirestore, private val storage: FirebaseStorage) {

    suspend fun deleteBeedFirestore(beed : Beed){
        firestore.collection("beeds").document(beed.title).delete()
            .addOnFailureListener { e -> Log.e("Firebase", "Error while deleting beed", e) }
    }

    suspend fun deleteBeedImage(beed: Beed){
        val ref = storage.getReferenceFromUrl(beed.imageURL).delete()
            .addOnFailureListener { e -> Log.e("Firebase", "Error while deleting beed image", e) }

    }

    suspend fun getBeedsFromFirestore() : pt.coimbra.neuza.beedsstore.authentication.model.Result<List<Beed>> =
        try{
            val snapshot = firestore.collection("beeds").get().await()
            val beeds = snapshot.map { it.toObject(Beed::class.java) }
            pt.coimbra.neuza.beedsstore.authentication.model.Result.Success(beeds)
        }catch (e: Exception){
            Result.Error(e)
        }
}