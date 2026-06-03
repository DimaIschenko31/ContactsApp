package com.example.contactsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.local.ContactEntity
import com.example.contactsapp.data.repository.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    val contacts = repository.contacts.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getContactById(id: Int) = repository.getContactById(id)

    fun addContact(name: String, phone: String, email: String) {
        viewModelScope.launch {
            repository.addContact(
                ContactEntity(name = name, phone = phone, email = email)
            )
        }
    }

    fun updateContact(id: Int, name: String, phone: String, email: String) {
        viewModelScope.launch {
            repository.updateContact(
                ContactEntity(id = id, name = name, phone = phone, email = email)
            )
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            repository.deleteContact(ContactEntity(id = id, name = "", phone = ""))
        }
    }
}

// Фабрика для створення ViewModel з параметром
class ContactViewModelFactory(
    private val repository: ContactRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}