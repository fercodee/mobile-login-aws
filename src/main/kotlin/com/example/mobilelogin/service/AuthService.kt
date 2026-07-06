package com.example.mobilelogin.service

import com.example.mobilelogin.model.SmsCode
import com.example.mobilelogin.model.User
import com.example.mobilelogin.repository.SmsCodeRepository
import com.example.mobilelogin.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val smsCodeRepository: SmsCodeRepository,
    private val smsService: SmsService
) {

    @Transactional
    fun processLogin(phone: String, uuid: String): LoginResult {
        val user = userRepository.findByPhone(phone)

        if (user != null && user.uuid == uuid && user.active) {
            return LoginResult.Success(user)
        }

        // Phone doesn't exist or uuid is different, send SMS
        val code = generateCode()
        
        // Remove old codes for this phone to ensure only latest is valid
        smsCodeRepository.deleteByPhone(phone)
        
        val smsCode = SmsCode(
            phone = phone,
            uuid = uuid,
            code = code,
            expiresAt = LocalDateTime.now().plusMinutes(5)
        )
        smsCodeRepository.save(smsCode)
        
        smsService.sendSms(phone, code)
        
        return LoginResult.SmsSent
    }

    @Transactional
    fun processConfirm(phone: String, uuid: String, code: String): User? {
        val smsCode = smsCodeRepository.findByPhoneAndUuidAndCode(phone, uuid, code)
        
        if (smsCode == null || smsCode.expiresAt.isBefore(LocalDateTime.now())) {
            return null
        }
        
        val user = userRepository.findByPhone(phone)
        val finalUser: User
        
        if (user == null) {
            finalUser = User(phone = phone, uuid = uuid, active = true)
        } else {
            user.uuid = uuid
            user.active = true
            finalUser = user
        }
        
        val savedUser = userRepository.save(finalUser)
        smsCodeRepository.delete(smsCode)
        
        return savedUser
    }

    private fun generateCode(): String {
        return Random.nextInt(100000, 999999).toString()
    }
}

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    object SmsSent : LoginResult()
}
