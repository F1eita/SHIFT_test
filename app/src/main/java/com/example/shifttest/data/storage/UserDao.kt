package com.example.shifttest.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    companion object {
        const val USER_ENTITY_NAME = "user_entity"
    }

    @Query("SELECT * FROM $USER_ENTITY_NAME")
    suspend fun getUsers(): List<UserEntity>

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(userEntities: List<UserEntity>)

    @Query("DELETE FROM $USER_ENTITY_NAME")
    suspend fun cleanTable()

    @Query("SELECT * FROM $USER_ENTITY_NAME WHERE id = :id")
    suspend fun getUser(id: String): UserEntity

}