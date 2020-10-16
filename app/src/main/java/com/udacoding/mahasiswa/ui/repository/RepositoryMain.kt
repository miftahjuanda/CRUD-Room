package com.udacoding.mahasiswa.ui.repository

import android.content.Context
import com.udacoding.mahasiswa.db.mahasiswa.DbMahasiswa
import com.udacoding.mahasiswa.db.mahasiswa.Mahasiswa
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryMain(context: Context) {

    private var dbMahasiswa: DbMahasiswa = DbMahasiswa.getInstance(context)!!

    fun loadData(response: (List<Mahasiswa>) -> Unit, error: (Throwable) -> Unit) {
        Observable.fromCallable { dbMahasiswa.mahasiswaDao().getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response(it)
            }, {
                error(it)
            })
    }


    fun addData(nama: String, nim: String, jurusan: String, alamat: String) {
        val mahasiswa = Mahasiswa(null, nama, nim, jurusan, alamat)
        Observable.fromCallable { dbMahasiswa.mahasiswaDao().insert(mahasiswa) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun updateData(id: Int, nama: String, nim: String, jurusan: String, alamat: String) {
        val mahasiswa = Mahasiswa(id, nama ?: "", nim ?: "", jurusan ?: "", alamat ?: "")
        Observable.fromCallable { dbMahasiswa.mahasiswaDao().update(mahasiswa) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun deleteData(mahasiswa: Mahasiswa?) {
        Observable.fromCallable { dbMahasiswa.mahasiswaDao().delete(mahasiswa) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
}