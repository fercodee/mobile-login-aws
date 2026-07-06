package com.example.mobilelogin.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sms_codes")
data class SmsCode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val uuid: String,

    @Column(nullable = false)
    val code: String,

    @Column(nullable = false)
    val expiresAt: LocalDateTime
)
