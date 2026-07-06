package com.example.mobilelogin.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val phone: String,

    @Column(nullable = false)
    var uuid: String,

    @Column(nullable = true)
    var name: String? = null,

    @Column(nullable = true)
    var description: String? = null,

    @Column(nullable = false)
    var active: Boolean = false
)
