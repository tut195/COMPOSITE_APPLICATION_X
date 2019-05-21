package com.babenkovladimir.composite_application_x.rx.rx_repository.repository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "firstName") val first: String,
    @ColumnInfo(name = "lastName") val last: String
)