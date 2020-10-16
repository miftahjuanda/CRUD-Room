package com.udacoding.mahasiswa.ui.beranda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.helper.SessionManager
import com.udacoding.mahasiswa.ui.register.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val session = SessionManager(context)

        tv_nama_profile.text = session.name
        tv_email_profile.text = session.email
        tv_nim_profile.text = session.nim
        tv_jurusan_profile.text = session.jurusan


        btn_logout.setOnClickListener {

            AlertDialog.Builder(requireActivity()).apply {
                setTitle("Logout")
                setMessage("Anda yakin keluar dari akun ini?")

                setPositiveButton("yakin") { dialog, i ->
                    session.logout()
                    var intent = Intent(context, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                }

                setNegativeButton("Batal") { dialog, i ->
                    dialog.dismiss()
                }
            }.show()
        }
    }
}
