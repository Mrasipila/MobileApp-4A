package com.example.app4a.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.example.app4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.w3c.dom.Text

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

        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(),password_edit.text.toString().trim())
        }

        login_edit.addTextChangedListener(loginTextWatcher);
        password_edit.addTextChangedListener(loginTextWatcher);
    }

    private val loginTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val regex = "[a-zA-Z0-9]{1,16}@[a-zA-Z0-9]{4,8}.[a-zA-Z]{3}".toRegex()
            if (regex.matches(login_edit.text.toString().trim()) && !password_edit.text.toString().trim().isEmpty()) {
                login_button.isEnabled=true
            } else {
                login_button.isEnabled=false
            }
        }
    };
}