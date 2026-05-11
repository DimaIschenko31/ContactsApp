package com.example.contactsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.contactsapp.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactViewModel : ViewModel() {

    private var nextId = 4

    private val _contacts = MutableStateFlow(
        listOf(
            Contact(id = 1, name = "Олена Коваль",    phone = "+380501234567", email = "olena@example.com"),
            Contact(id = 2, name = "Максим Бондар",   phone = "+380671234567", email = "max@example.com"),
            Contact(id = 3, name = "Софія Мельник",   phone = "+380931234567", email = "sofia@example.com")
        )
    )
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    fun addContact(name: String, phone: String, email: String) {
        val new = Contact(id = nextId++, name = name, phone = phone, email = email)
        _contacts.value = _contacts.value + new
    }

    fun deleteContact(id: Int) {
        _contacts.value = _contacts.value.filter { it.id != id }
    }

    fun updateContact(id: Int, name: String, phone: String, email: String) {
        _contacts.value = _contacts.value.map { c ->
            if (c.id == id) c.copy(name = name, phone = phone, email = email) else c
        }
    }

    fun getContact(id: Int): Contact? = _contacts.value.find { it.id == id }
}