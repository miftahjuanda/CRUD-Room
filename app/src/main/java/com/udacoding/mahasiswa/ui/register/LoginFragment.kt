package com.udacoding.mahasiswa.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.db.user.User
import com.udacoding.mahasiswa.helper.SessionManager
import com.udacoding.mahasiswa.ui.register.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        navController = Navigation.findNavController(view)

        tv_registrasi.setOnClickListener(this)
        btn_login.setOnClickListener(this)

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.response.observe(viewLifecycleOwner, Observer { onSucces(it) })
        viewModel.isEmpty.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.onError.observe(viewLifecycleOwner, Observer { onDataError(it) })
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.tv_registrasi -> navController.navigate(R.id.action_loginFragment_to_regist1Fragment)

            R.id.btn_login -> {
                val email = edt_email_login.text.toString()
                val pass = edt_password_login.text.toString()

                when {
                    email.isEmpty() -> {
                        edt_email_login.error = "Email Harus Diisi"
                    }

                    pass.isEmpty() -> {
                        edt_password_login.error = "Password harus Diisi"
                    }
                    else -> {
                        viewModel.getLogin(email, pass)

                    }
                }
            }
        }
    }

    private fun onDataError(it: Throwable?) {
        Toast.makeText(context, "Email atau password salah", Toast.LENGTH_SHORT).show()
    }

    private fun onSucces(users: User?) {
        var session= SessionManager(context)
        session?.email = users?.email
        session?.name = users?.nama
        session?.nim = users?.nim
        session?.jurusan = users?.jurusan
        session?.login = true

        navController.navigate(R.id.action_loginFragment_to_berandaActivity)
    }

    private fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}