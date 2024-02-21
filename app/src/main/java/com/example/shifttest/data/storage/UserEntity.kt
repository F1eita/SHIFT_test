package com.example.shifttest.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shifttest.data.storage.UserDao.Companion.USER_ENTITY_NAME
import com.example.shifttest.domain.User
import com.example.shifttest.domain.UserInfo

@Entity(
    tableName = USER_ENTITY_NAME
)
data class UserEntity(
    @PrimaryKey val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dob: String,
    val age: Int,
    val phone: String,
    val cell: String,
    val picture: String,
    val nat: String,
    val latitude: String,
    val longitude: String,
    val city: String,
    val state: String,
    val country: String,
    val streetName: String,
    val streetNumber: Int
)

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = "$firstName $lastName",
        picture = picture,
        address = "$country, $state, $city",
        number = phone
    )
}

fun UserEntity.toUserInfo(): UserInfo {
    return UserInfo(
        id = id,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        email = email,
        dob = dob,
        age = age,
        phone = phone,
        cell = cell,
        picture = picture,
        nat = nat,
        latitude = latitude,
        longitude = longitude,
        city = city,
        state = state,
        country = country,
        streetName = streetName,
        streetNumber = streetNumber
    )
}
