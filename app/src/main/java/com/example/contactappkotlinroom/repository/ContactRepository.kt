package com.example.contactappkotlinroom.repository

import com.example.contactappkotlinroom.room.Dao
import com.example.contactappkotlinroom.room.EntityClass


/**
 * it act as a bridge  between Room database and view model
 * it interact with the database with the help of doa with the methods insert,update,delete
 */

class ContactRepository(private val doa:Dao) {

    val contacts = doa.getAllData()

    suspend fun  insertContact(contact:EntityClass):Long{
        return doa.insertContact(contact)
    }

    suspend fun updateContact(contact:EntityClass){
        return doa.updateContact(contact)
    }

     suspend fun deleteContact(contact:EntityClass){
          return doa.deleteContact(contact)
     }

    suspend fun deleteAll(){
        return doa.deleteAll()
    }

}