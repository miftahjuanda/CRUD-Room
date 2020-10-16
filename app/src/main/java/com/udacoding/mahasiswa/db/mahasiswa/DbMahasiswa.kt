package com.udacoding.mahasiswa.db.mahasiswa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mahasiswa::class), version = 1)

abstract class DbMahasiswa : RoomDatabase() {

    abstract fun mahasiswaDao(): DaoMahasiswa

    companion object {
        private var INSTANCE: DbMahasiswa? = null

        fun getInstance(context: Context): DbMahasiswa? {
            if (INSTANCE == null) {
                synchronized(DbMahasiswa::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DbMahasiswa::class.java,
                        "dbmahasiswa.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}