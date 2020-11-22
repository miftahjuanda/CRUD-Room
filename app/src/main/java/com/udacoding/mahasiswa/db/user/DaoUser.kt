package com.udacoding.mahasiswa.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoUser {

    @Query("SELECT * FROM user")
    fun getUser(): List<User>

    //jika terdapat data ganda maka akan di replace dengan data yg terbaru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE email= :email AND password= :password")
    fun login (email: String, password: String):User

}