package com.example.contactsapp.model

data class Contact(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String = ""
)