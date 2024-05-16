package com.example.maktabuhalafiq.id
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Singleton
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
    @Provides
    @Singleton
    fun provideAuthReference(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
