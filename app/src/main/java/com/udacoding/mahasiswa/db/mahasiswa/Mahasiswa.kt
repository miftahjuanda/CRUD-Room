package com.udacoding.mahasiswa.db.mahasiswa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class Mahasiswa(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "nim")
    var nim: String? = null,

    @ColumnInfo(name = "jurusan")
    var jurusan: String? = null,

    @ColumnInfo(name = "alamat")
    var alamat: String? = null
)