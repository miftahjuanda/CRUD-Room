package com.udacoding.mahasiswa.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacoding.mahasiswa.R
import kotlinx.android.synthetic.main.fragment_regist2.*

class Regist2Fragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    var get_nama: String? = null
    var get_nim: String? = null
    var get_jurusan: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regist2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btn_daftar_regist.setOnClickListener(this)
        tv_back_regist2.setOnClickListener(this)

        tv_welcome_regist2.text = "Selamat Datang $get_nama, \nLengkapi Isian Dibawah Ini"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_nama = arguments?.getString("nama")
        get_nim = arguments?.getString("nim")
        get_jurusan = arguments?.getString("jurusan")

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_daftar_regist -> {
                if (edt_password_regist.text.toString().isEmpty()) {
                    edt_password_regist.error = "Password Harus Diisi"

                } else if (edt_email_regist.text.toString().isEmpty()) {
                    edt_email_regist.error = "Email Harus Diisi"

                } else if (edt_passwordConfirm_regist.text.toString().isEmpty()) {
                    edt_passwordConfirm_regist.error = "Password Harus Diisi"

                } else if (edt_password_regist.text.toString().length <= 6) {
                    Toast.makeText(context, "Password Minimal 6 karakter", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    if (edt_password_regist.text.toString()
                            .equals(edt_passwordConfirm_regist.text.toString())
                    ) {
                        val bundle = bundleOf(
                            "nama" to get_nama, "nim" to get_nim, "jurusan" to get_jurusan,
                            "email" to edt_email_regist.text.toString(), "pass" to edt_password_regist.text.toString()
                        )
                        navController.navigate(R.id.action_regist2Fragment_to_resultFragment, bundle)
                    } else {
                        Toast.makeText(context, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.tv_back_regist2 -> activity?.onBackPressed()
        }
    }
}