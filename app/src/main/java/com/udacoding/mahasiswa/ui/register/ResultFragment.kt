package com.udacoding.mahasiswa.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.ui.register.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var viewModel: RegisterViewModel

    var get_nama: String? = null
    var get_nim: String? = null
    var get_jurusan: String? = null
    var get_email: String? = null
    var get_password: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        get_nama = arguments?.getString("nama")
        get_nim = arguments?.getString("nim")
        get_jurusan = arguments?.getString("jurusan")
        get_email = arguments?.getString("email")
        get_password = arguments?.getString("pass")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        tv_back_result.setOnClickListener(this)
        tv_welcome_konfirmasi.text =
            "Koreksi Data Anda $get_nama,\nJika Tidak Sesuai kembali ke Halaman Sebelumya dan Perbaiki"

        tv_nama_result.text = get_nama
        tv_email_result.text = get_email
        tv_nim_result.text = get_nim
        tv_jurusan_result.text = get_jurusan

        initData()
        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseCheck.observe(viewLifecycleOwner, Observer { onSuccess(it) })
        viewModel.isValidator.observe(viewLifecycleOwner, Observer { errorMassage(it) })
        viewModel.errorValidator.observe(viewLifecycleOwner, Observer { suksesAdd(it) })

    }

    private fun suksesAdd(it: String?) {
        Toast.makeText(context, "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
        viewModel.addUser(
            get_nama.toString(), get_email.toString(),
            get_nim.toString(), get_jurusan.toString(), get_password.toString())

        navController.navigate(R.id.action_resultFragment_to_loginFragment)
    }

    private fun onSuccess(it: Int) {
        Toast.makeText(context, "Gagal tambah Data", Toast.LENGTH_SHORT).show()
    }

    private fun errorMassage(it: String?) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    private fun initData() {
        btn_konfirmasi.setOnClickListener {
            viewModel.validator(get_email.toString())

        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_back_result -> activity?.onBackPressed()
        }
    }
}