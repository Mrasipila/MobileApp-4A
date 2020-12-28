package com.example.app4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.app4a.R
import com.example.app4a.presentation.main.buttonStatus.LoginError
import com.example.app4a.presentation.main.buttonStatus.LoginSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

const val EXTRA_MESSAGE = "com.example.app4a.presentation.main.MESSAGE"
const val EXTRA_MESSAGE0 = "com.example.app4a.presentation.main.MESSAGE0"

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Le système réagit en fonction de la valeur de livedata (fetch data from db, if found -> Success, no data found -> error)
        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    val message = "MainToApp"
                    val intent = Intent(this, AppListActivity::class.java).apply {
                        putExtra(EXTRA_MESSAGE0, message)
                    }
                    startActivity(intent)
                }
                LoginError -> {
                    password_alert.isVisible = true
                }
            }
        })

        // Modifie la variable livedata dans ViewModel dés que l'user clic sur login (recupere ce qu'il a ecrit dans les champs login et password)
        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(),password_edit.text.toString().trim())
        }
    }

    /** Called when the user taps the Create Account button */
    fun goToCreateAccountPage(view: View) {
        val message = "MainToSignIn"
        val intent = Intent(this, CreateAccountActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}