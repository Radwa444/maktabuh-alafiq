package com.example.maktabuhalafiq.data.repository.catagories
import android.util.Log
import com.example.maktabuhalafiq.data.models.Categories
import com.example.maktabuhalafiq.utils.UiState
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference) :CategoriesRepository{
    override suspend fun getCategories(): UiState<List<Categories>> {
        return try {
            val categoryList= mutableListOf<Categories>()
            val snapshat=databaseReference.get().await()
            for(categorySnapshot in snapshat.children){
                val category = categorySnapshot.getValue(Categories::class.java)
               category?.let {
                   categoryList.add(it)
                   Log.e("TAG",it.toString())
               }

            }
            UiState.Success(categoryList)


        }catch (e:Exception){
            Log.e("TAGError",UiState.Failure(e.message).toString())
            UiState.Failure(e.message)

        }

    }



}
