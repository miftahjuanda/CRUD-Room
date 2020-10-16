package com.udacoding.mahasiswa.ui.beranda

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.adapter.MahasiswaAdapter
import com.udacoding.mahasiswa.db.mahasiswa.DbMahasiswa
import com.udacoding.mahasiswa.db.mahasiswa.Mahasiswa
import com.udacoding.mahasiswa.ui.beranda.viewModel.ViewModelMahasiswa
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_mahasiswa.*

class MahasiswaFragment : Fragment() {

    private var dbMahasiswa: DbMahasiswa? = null
    private var dialogView: Dialog? = null
    private lateinit var viewModel: ViewModelMahasiswa

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mahasiswa, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelMahasiswa::class.java)
        dbMahasiswa = context?.let { DbMahasiswa.getInstance(it) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            showDialog()
        }

        viewModel.getData()

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseGetData.observe(viewLifecycleOwner, Observer { showData(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    //load Data
    private fun showData(item: List<Mahasiswa>) {
        rv_list.adapter = item.let {
            MahasiswaAdapter(item, object : MahasiswaAdapter.Onclick {
                override fun update(item: Mahasiswa?) {
                    showUpdateDialog(item)
                }

                override fun delete(item: Mahasiswa?) {
                    context?.let { it1 ->
                        AlertDialog.Builder(it1).apply {
                            setTitle("Hapus Data")
                            setMessage("Anda yakin menghapus data tersebut?")

                            setPositiveButton("yakin") { dialog, i ->
                                viewModel.deleteData(item)
                                viewModel.getData()

                            }

                            setNegativeButton("Batal") { dialog, i ->
                                dialog.dismiss()
                            }
                        }.show()
                    }
                }

            })
        }
    }

    //addDialog
    private fun showDialog() {
        val dialog = context?.let { AlertDialog.Builder(it) }
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        dialog?.setView(view)

        view.btn_save_add.setOnClickListener {

            val nama = view.edt_nama_add.text.toString()
            val nim = view.edt_nim_add.text.toString()
            val jurusan = view.edt_jurusan_add.text.toString()
            val alamat = view.edt_alamat_add.text.toString()

            if (nama.isEmpty()) {
                view.edt_nama_add.error = "Isi Nama"
            } else if (nim.isEmpty()) {
                view.edt_nim_add.error = "Isi NIM"
            } else if (jurusan.isEmpty()) {
                view.edt_jurusan_add.error = "Isi Jurusan"
            } else if (alamat.isEmpty()) {
                view.edt_alamat_add.error = "Isi Alamat"

            } else {
                viewModel.addData(nama, nim, jurusan, alamat)
                dialogView?.dismiss()
                viewModel.getData()

            }
        }

        view.btn_cancel_add.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog?.create()
        dialogView?.setCancelable(false)
        dialogView?.show()
    }

    //uodateDialog
    private fun showUpdateDialog(item: Mahasiswa?) {
        val dialog = context?.let { AlertDialog.Builder(it) }
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        dialog?.setView(view)

        view.btn_save_add.text = getString(R.string.update)

        view.edt_nama_add.setText(item?.nama)
        view.edt_nim_add.setText(item?.nim)
        view.edt_jurusan_add.setText(item?.jurusan)
        view.edt_alamat_add.setText(item?.alamat)

        view.btn_save_add.setOnClickListener {

            val nama = view.edt_nama_add.text.toString()
            val nim = view.edt_nim_add.text.toString()
            val jurusan = view.edt_jurusan_add.text.toString()
            val alamat = view.edt_alamat_add.text.toString()

            if (nama.isEmpty()) {
                view.edt_nama_add.error = "Isi Nama"
            } else if (nim.isEmpty()) {
                view.edt_nim_add.error = "Isi NIM"
            } else if (jurusan.isEmpty()) {
                view.edt_jurusan_add.error = "Isi Jurusan"
            } else if (alamat.isEmpty()) {
                view.edt_alamat_add.error = "Isi Alamat"
            } else {
                item?.id?.let { it1 -> viewModel.updateData(it1, nama, nim, jurusan, alamat) }
                dialogView?.dismiss()
                viewModel.getData()
            }
        }

        view.btn_cancel_add.setOnClickListener {
            dialogView?.dismiss()
        }

        dialogView = dialog?.create()
        dialogView?.setCancelable(false)
        dialogView?.show()
    }
}