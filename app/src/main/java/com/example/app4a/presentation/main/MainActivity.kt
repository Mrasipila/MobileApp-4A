package com.example.app4a.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.app4a.R
import com.example.app4a.presentation.textWatcher.LoginTW
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    //TODO Navigate
                }
                LoginError -> MaterialAlertDialogBuilder(this)
                    .setTitle("Erreur")
                    .setMessage("Compte inconnu")
                    .setPositiveButton("Ok") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        })

        val textWatcher : TextWatcher = LoginTW(login_edit.text)
        login_edit.setError("You ")
        login_edit.addTextChangedListener(textWatcher);

        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(),password_edit.text.toString().trim())
        }
    }
}