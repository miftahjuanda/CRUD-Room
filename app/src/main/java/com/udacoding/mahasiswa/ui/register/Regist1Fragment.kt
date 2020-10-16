package com.udacoding.mahasiswa.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacoding.mahasiswa.R
import kotlinx.android.synthetic.main.fragment_regist1.*

class Regist1Fragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regist1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btn_next.setOnClickListener(this)
        tv_back_regist1.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_next -> {
                if (edt_nama.text.toString().isEmpty()) {
                    edt_nama.error = "Nama Harus Diisi"
                } else if (edt_nim.text.toString().isEmpty()) {
                    edt_nim.error = "Nim Harus Diisi"
                } else if (edt_jurusan.text.toString().isEmpty()) {
                    edt_jurusan.error = "Nim Harus Diisi"
                } else {
                    val bundle = bundleOf(
                        "nama" to edt_nama.text.toString(),
                        "nim" to edt_nim.text.toString(),
                        "jurusan" to edt_jurusan.text.toString()
                    )
                    navController.navigate(R.id.action_regist1Fragment_to_regist2Fragment, bundle)
                }
            }

            R.id.tv_back_regist1 -> activity?.onBackPressed()
        }
    }
}