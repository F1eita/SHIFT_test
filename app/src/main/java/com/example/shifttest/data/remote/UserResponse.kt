package com.example.shifttest.data.remote

import com.example.shifttest.domain.User

data class UserResponse(
    val gender: String,
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val login: LoginResponse,
    val dob: DobResponse,
    val phone: String,
    val cell: String,
    val picture: PictureResponse,
    val nat: String
)

data class NameResponse(
    val first: String,
    val last: String
)

data class DobResponse(
    val date: String,
    val age: Int
)

data class LoginResponse(
    val uuid: String,
    val username: String
)

data class PictureResponse(
    val medium: String,
    val thumbnail: String
)

fun UserResponse.toUser(): User {
    return User(
        id = login.uuid,
        name = name.first + " " + name.last,
        picture = picture.medium,
        address = location.country + ", " + location.state + ", " + location.city,
        number = phone
    )
}