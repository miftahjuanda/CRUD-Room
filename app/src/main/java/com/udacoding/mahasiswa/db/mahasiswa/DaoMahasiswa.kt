package com.udacoding.mahasiswa.db.mahasiswa

import androidx.room.*

@Dao
interface DaoMahasiswa {

    @Query("SELECT * FROM mahasiswa")
    fun getData(): List<Mahasiswa>

    @Insert
    fun insert(mahasiswa: Mahasiswa)

    @Update
    fun update(mahasiswa: Mahasiswa?)

    @Delete
    fun delete(mahasiswa: Mahasiswa?)
}