package pt.coimbra.neuza.beedsstore.adminpanel.addcatalog.data

import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed
import pt.coimbra.neuza.beedsstore.adminpanel.model.beedList
import pt.coimbra.neuza.beedsstore.authentication.model.Result
import java.io.IOException
import java.util.UUID

class BeedRepository( private val firestore: FirebaseFirestore, private val storage: FirebaseStorage) {

    suspend fun saveBeedToFirestore(beed : Beed){
        firestore.collection("beeds").document(beed.title).set(beed).await()
    }

    suspend fun getBeedsFromFirestore() : Result<List<Beed>> =
        try{
            val snapshot = firestore.collection("beeds").get().await()
            val beeds = snapshot.map { it.toObject(Beed::class.java) }
            Result.Success(beeds)
        }catch (e: Exception){
            Result.Error(e)
        }

    suspend fun uploadImage(uri: Uri, context: Context): String {
        val ref = storage.reference.child("beeds/${UUID.randomUUID()}.jpg")

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IOException("Não foi possível abrir a imagem")

        ref.putStream(inputStream).await()
        return ref.downloadUrl.await().toString()
    }

    suspend fun createBeedWithImage(imageUri: Uri, beed: Beed, context: Context){
        val url = uploadImage(imageUri, context)
        saveBeedToFirestore(beed.copy(imageURL = url))
    }
}
