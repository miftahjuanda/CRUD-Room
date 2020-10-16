package com.udacoding.mahasiswa.ui.beranda.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.udacoding.mahasiswa.db.mahasiswa.Mahasiswa
import com.udacoding.mahasiswa.ui.repository.RepositoryMain

class ViewModelMahasiswa(application: Application) : AndroidViewModel(application) {

    val repository = RepositoryMain(application.applicationContext)

    var responseGetData = MutableLiveData<List<Mahasiswa>>()
    var isError = MutableLiveData<Throwable>()

    fun getData() {
        repository.loadData({
            responseGetData.value = it
        }, {
            isError.value = it
        })
    }

    fun addData(nama: String, nim: String, jurusan: String, alamat: String) {
        repository.addData(nama, nim, jurusan, alamat)
    }

    fun updateData(id: Int, nama: String, nim: String, jurusan: String, alamat: String) {
        repository.updateData(id, nama, nim, jurusan, alamat)
    }

    fun deleteData(mahasiswa: Mahasiswa?) {
        repository.deleteData(mahasiswa)
    }
}