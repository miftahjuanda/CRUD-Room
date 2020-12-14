package com.udacoding.mahasiswa.ui.register.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.udacoding.mahasiswa.db.user.User
import com.udacoding.mahasiswa.ui.repository.RepositoryRegister

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val repositoryRegist = RepositoryRegister(application.applicationContext)

    var onError = MutableLiveData<Throwable>()
    var onErrorCheck = MutableLiveData<Throwable>()
    var onErrorAdd = MutableLiveData<Throwable>()
    var response = MutableLiveData<User>()
    var responseAdd = MutableLiveData<Unit>()
    var responseCheck = MutableLiveData<Int>()
    var isEmpty = MutableLiveData<String>()
    var isValidator = MutableLiveData<String>()
    var errorValidator = MutableLiveData<String>()

    fun addUser(nama: String, email: String, nim: String, jurusan: String, password: String) {

        repositoryRegist.addUser(nama, email, nim, jurusan, password, {
            responseAdd.value = it
        }, {
            onErrorAdd.value = it
        })

    }

    fun getLogin(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            repositoryRegist.getLogin(email, password, {
                response.value = it

            }, {
                onError.value = it
            })

        } else {
            isEmpty.value = "Lengkapi Username dan Password"
        }
    }

    fun validator(email: String) {
        repositoryRegist.validaotor(email,
            {
                responseCheck.value = it
                if (it > 0) {
                    isValidator.value = "Email Telah terdaftar"
                } else {
                    errorValidator.value = "testt"
                }

            }, {
                onErrorCheck.value = it
            })
    }
}