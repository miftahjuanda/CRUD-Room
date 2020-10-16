package com.udacoding.mahasiswa.ui.repository

import android.content.Context
import com.udacoding.mahasiswa.db.user.DbUser
import com.udacoding.mahasiswa.db.user.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryRegister(contex: Context) {

    private var dbUser: DbUser? = DbUser.getInstance(contex)

    fun addUser(nama: String, email: String, nim: String, jurusan: String, password: String) {
        val user = User(null, nama, email, nim, jurusan, password)
        Observable.fromCallable { dbUser?.userDao()?.insert(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun getLogin(
        email: String,
        password: String,
        responseHandle: (User?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { dbUser?.userDao()?.login(email, password) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandle(it)
            }, {
                errorHandler(it)
            })
    }
}