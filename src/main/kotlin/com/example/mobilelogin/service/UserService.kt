package com.example.mobilelogin.service

import com.example.mobilelogin.dto.UpdateUserRequest
import com.example.mobilelogin.model.User
import com.example.mobilelogin.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun updateUser(id: Long, request: UpdateUserRequest): User? {
        val user = userRepository.findById(id).orElse(null) ?: return null
        
        request.name?.let { user.name = it }
        request.description?.let { user.description = it }
        
        return userRepository.save(user)
    }
}
