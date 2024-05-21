package com.example.maktabuhalafiq.id
import android.app.Application
import android.content.Context
import com.example.maktabuhalafiq.data.repository.auth.AuthRepository
import com.example.maktabuhalafiq.data.repository.auth.AuthRepositoryImpl
import com.example.maktabuhalafiq.data.repository.books.BooksRepository
import com.example.maktabuhalafiq.data.repository.books.BooksRepositoryImpl
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepository
import com.example.maktabuhalafiq.data.repository.catagories.CategoriesRepositoryImpl
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepository
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth,databaseReference: DatabaseReference): AuthRepository {
        return AuthRepositoryImpl(auth,databaseReference.child("User"))
    }
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(
       context: Context
    ): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(context)


    }
}
