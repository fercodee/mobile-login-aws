package com.example.mobilelogin.controller

import com.example.mobilelogin.dto.ConfirmRequest
import com.example.mobilelogin.dto.LoginRequest
import com.example.mobilelogin.dto.UpdateUserRequest
import com.example.mobilelogin.model.User
import com.example.mobilelogin.service.AuthService
import com.example.mobilelogin.service.LoginResult
import com.example.mobilelogin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val authService: AuthService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        return when (val result = authService.processLogin(request.phone, request.uuid)) {
            is LoginResult.Success -> ResponseEntity.ok(result.user)
            is LoginResult.SmsSent -> ResponseEntity.status(HttpStatus.ACCEPTED).body(mapOf("message" to "SMS confirmation sent"))
        }
    }

    @PostMapping("/confirm")
    fun confirm(@RequestBody request: ConfirmRequest): ResponseEntity<Any> {
        val user = authService.processConfirm(request.phone, request.uuid, request.code)
        
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Invalid or expired confirmation code"))
        }
    }

    @PutMapping("/{id}")
    fun updateProfile(
        @PathVariable id: Long,
        @RequestBody request: UpdateUserRequest
    ): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, request)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
