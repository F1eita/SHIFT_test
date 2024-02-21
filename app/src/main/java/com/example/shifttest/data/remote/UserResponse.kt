package com.example.shifttest.data.remote

import com.example.shifttest.data.storage.UserEntity
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
    val uuid: String
)

data class PictureResponse(
    val medium: String
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

fun UserResponse.toUserEntity(): UserEntity{
    return UserEntity(
        id = login.uuid,
        firstName = name.first,
        lastName = name.last,
        gender = gender,
        email = email,
        dob = dob.date,
        age = dob.age,
        phone = phone,
        cell = cell,
        picture = picture.medium,
        nat = nat,
        latitude = location.coordinates.latitude,
        longitude = location.coordinates.longitude,
        city = location.city,
        country = location.country,
        state = location.state,
        streetName = location.street.name,
        streetNumber = location.street.number
    )
}