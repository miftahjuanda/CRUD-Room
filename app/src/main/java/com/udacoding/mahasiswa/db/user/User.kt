package com.udacoding.mahasiswa.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "nim")
    var nim: String? = null,

    @ColumnInfo(name = "jurusan")
    var jurusan: String? = null,

    @ColumnInfo(name = "password")
    var password: String? = null
)