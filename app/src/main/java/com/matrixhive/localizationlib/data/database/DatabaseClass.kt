package com.matrixhive.localizationlib.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

//Todo remove this comments and add entity names and interface dao files
//Enter any entity class name here
//@Database(entities = [SplashResponse::class], version = 1, exportSchema = false)
abstract class DatabaseClass:RoomDatabase() {

//    abstract fun getDao():ImageDao
}