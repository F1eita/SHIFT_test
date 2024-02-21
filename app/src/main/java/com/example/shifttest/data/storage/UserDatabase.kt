package com.example.shifttest.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shifttest.data.storage.UserDatabase.Companion.DATABASE_VERSION

@Database(
    entities = [UserEntity::class],
    version = DATABASE_VERSION,
)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_VERSION = 1
    }

    abstract fun createInspectionDao(): UserDao
}