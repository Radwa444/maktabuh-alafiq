package com.example.maktabuhalafiq.data.repository.author
import android.util.Log
import com.example.maktabuhalafiq.data.models.Author
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthorRepositoryImpl@Inject constructor(private val databaseReference: DatabaseReference):AuthorRepository {
    override suspend fun getAuthor(): UiState<List<Author>> {
        return try {
            val authorList= mutableListOf<Author>()
            val snapshat=databaseReference.get().await()
            for(authorSnapshot in snapshat.children){
                val author = authorSnapshot.getValue(Author::class.java)
                author?.let {
                    authorList.add(it)
                    Log.e("TAG",it.toString())
                } }
            UiState.Success(authorList)
        }catch (e:Exception){
            Log.e("TAGError",UiState.Failure(e.message).toString())
            UiState.Failure(e.message)
        }
    }
}