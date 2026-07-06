package com.example.mobilelogin.repository

import com.example.mobilelogin.model.SmsCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SmsCodeRepository : JpaRepository<SmsCode, Long> {
    fun findByPhoneAndUuidAndCode(phone: String, uuid: String, code: String): SmsCode?
    fun deleteByPhone(phone: String)
}
