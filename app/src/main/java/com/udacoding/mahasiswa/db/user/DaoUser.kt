package com.udacoding.mahasiswa.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoUser {

    @Query("SELECT * FROM user")
    fun getUser(): List<User>

    @Insert
    fun insert(user: User)

    @Query("SELECT COUNT(email) FROM user WHERE email= :email")
    fun validator (email: String) : Int

    @Query("SELECT * FROM user WHERE email= :email AND password= :password")
    fun login (email: String, password: String):User

}