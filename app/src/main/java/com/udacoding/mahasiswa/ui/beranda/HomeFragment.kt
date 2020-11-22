package com.udacoding.mahasiswa.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.helper.SessionManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val session = SessionManager(context)

        tv_name_home.text = "Selamat Datang \n${session.name}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(shouldInterceptBackPress()){
                        activity?.finishAffinity()
                        activity?.finish()
                    }else{
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }

    private fun shouldInterceptBackPress(): Boolean {
        return true
    }

}