package com.example.contactsapp.data.repository

import com.example.contactsapp.data.local.ContactDao
import com.example.contactsapp.data.local.ContactEntity

class ContactRepository(private val contactDao: ContactDao) {

    val contacts = contactDao.getAllContacts()

    fun getContactById(id: Int) = contactDao.getContactById(id)

    suspend fun addContact(contact: ContactEntity) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: ContactEntity) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: ContactEntity) {
        contactDao.deleteContact(contact)
    }
}