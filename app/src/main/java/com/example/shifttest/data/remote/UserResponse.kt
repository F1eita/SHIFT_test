package com.example.shifttest.data.remote

import com.example.shifttest.domain.User

data class UserResponse(
    val gender: String,
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val dob: DobResponse,
    val registered: RegisteredResponse,
    val phone: String,
    val cell: String,
    val id: IdResponse,
    val picture: PictureResponse,
    val nat: String
)

data class NameResponse(
    val title: String,
    val first: String,
    val last: String
)

data class DobResponse(
    val date: String,
    val age: Int
)

data class RegisteredResponse(
    val date: String,
    val age: Int
)

data class IdResponse(
    val name: String,
    val value: String
)

data class PictureResponse(
    val medium: String,
    val thumbnail: String
)

fun UserResponse.toUser(): User {
    return User(
        id = id.value,
        name = name.first + " " + name.last,
        thumbnail = picture.thumbnail,
        address = location.country + ", " + location.state + ", " + location.city,
        number = phone
    )
}