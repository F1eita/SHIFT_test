package com.example.shifttest.di

import android.content.Context
import androidx.room.Room
import com.example.shifttest.data.storage.UserDao
import com.example.shifttest.data.storage.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "database.dp")
            .build()
    }

    @Provides
    fun provideDao(database: UserDatabase): UserDao {
        return database.createInspectionDao()
    }

}