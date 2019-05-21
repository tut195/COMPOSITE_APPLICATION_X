package com.babenkovladimir.composite_application_x.rx.rx_repository.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.babenkovladimir.composite_application_x.rx.rx_repository.repository.data.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}