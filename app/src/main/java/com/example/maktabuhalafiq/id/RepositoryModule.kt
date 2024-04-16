package com.example.maktabuhalafiq.id
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.data.repository.books.BooksRepositoryImpl
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepository
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepositoryImpl
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {



    @Provides
    @Singleton
    fun provideCategoryRepository(databaseReference: DatabaseReference): CategoriesRepository {
        return CategoriesRepositoryImpl(databaseReference.child("Categories"))
    }

    @Provides
    @Singleton
    fun provideBooksRepository(databaseReference: DatabaseReference): BooksRepository {
        return BooksRepositoryImpl(databaseReference)
    }
}
