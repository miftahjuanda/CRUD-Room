package com.udacoding.mahasiswa.db.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)

abstract class DbUser : RoomDatabase() {

    abstract fun userDao(): DaoUser

    companion object {
        private var INSTANCE: DbUser? = null

        fun getInstance(context: Context): DbUser? {
            if (INSTANCE == null) {
                synchronized(DbUser::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DbUser::class.java,
                        "dbuser.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}