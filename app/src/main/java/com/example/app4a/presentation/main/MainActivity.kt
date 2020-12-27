package com.example.app4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.example.app4a.R
import com.example.app4a.presentation.main.buttonStatus.LoginError
import com.example.app4a.presentation.main.buttonStatus.LoginSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

const val EXTRA_MESSAGE = "com.example.app4a.presentation.main.MESSAGE"

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Le système réagit en fonction de la valeur de livedata (fetch data from db, if found -> Success, no data found -> error)
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

        // Modifie la variable livedata dans ViewModel dés que l'user clic sur login (recupere ce qu'il a ecrit dans les champs login et password)
        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(),password_edit.text.toString().trim())
        }

        login_edit.addTextChangedListener(loginTextWatcher);
        password_edit.addTextChangedListener(loginTextWatcher);
    }

    /** Called when the user taps the Create Account button */
    fun goToCreateAccountPage(view: View) {
        val message = "MainToSignIn"
        val intent = Intent(this, CreateAccountActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    // Condition sur la vue -> L'utilisateur n'a pas le droit de violer certaines règles
    // On regarde en "temps réelle" ce qu'écrit l'user et on active le bouton en conséquence
    private val loginTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        // On aurait pu ajouter un oberver sur une live data et un Onclicklistener{}
        // Pour vérifier dans une fonction dans le viewModel si les champs sont remplis correctement
        // Mais on veut utiliser la fonctionnalité "disable button"
        // On considère qu'il s'agit de formatage du contenu UI donc n'a pas sa place dans le ViewModel
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Check for valid password & login
            val regex0 = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()
            val regex = "[a-zA-Z0-9]{1,16}@[a-zA-Z0-9]{4,8}.[a-zA-Z]{3}".toRegex()
            login_button.isEnabled = regex.matches(login_edit.text.toString().trim()) && regex0.matches(password_edit.text.toString().trim())
        }
    };

}