package com.example.contactappkotlinroom.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface Dao {

    @Insert
     suspend fun insertContact(entityClass: EntityClass):Long
     @Update
     suspend fun updateContact(entityClass: EntityClass)

     @Delete()
     suspend fun deleteContact(entityClass: EntityClass)

     @Query("DELETE FROM contact_table")
     suspend fun deleteAll()

     @Query("SELECT * FROM contact_table")
     fun getAllData():LiveData<List<EntityClass>>

}