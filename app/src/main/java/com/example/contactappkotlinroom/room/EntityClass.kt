package com.example.contactappkotlinroom.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_table")
data class EntityClass(


    @PrimaryKey(autoGenerate = true)
    var contact_Id:Int,
    var contact_Name:String,
    var contact_Number:String


)
