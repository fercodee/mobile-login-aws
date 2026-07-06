package com.example.mobilelogin.dto

data class LoginRequest(
    val phone: String,
    val uuid: String
)

data class ConfirmRequest(
    val phone: String,
    val uuid: String,
    val code: String
)

data class UpdateUserRequest(
    val name: String?,
    val description: String?
)
