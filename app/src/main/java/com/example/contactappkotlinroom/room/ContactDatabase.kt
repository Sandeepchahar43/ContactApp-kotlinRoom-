package com.example.contactappkotlinroom.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EntityClass::class], version = 1)
 abstract class ContactDatabase :RoomDatabase() {

    /**
     * DAO is imported INSIDE the Room database — because Room needs to know which DAO belongs to this database.
     */

    abstract val doa:Dao  // it is mandatory to declare this in room database
    /**
     * here we are using a singleton design pattern so that only one instance of the database is crated
     * to reduce that corruption,and slow database
     */

    companion object{

        @Volatile
         private var Instance: ContactDatabase?= null

         fun getInstance(context: Context): ContactDatabase? {

             synchronized(this){
                 var instance = Instance

                 if(instance == null){

                     // create a  new room database
                     instance = Room.databaseBuilder(context.applicationContext,
                         ContactDatabase::class.java,"contacts_db").build()
                 }
                 Instance =instance
                 return instance

             }


         }


    }

}