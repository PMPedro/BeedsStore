package pt.coimbra.neuza.beedsstore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object Injection {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val stoInstance: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    fun instance(): FirebaseFirestore {
        return instance
    }

    fun storageInstance(): FirebaseStorage{
        return stoInstance
    }
}