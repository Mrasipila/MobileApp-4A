package com.example.app4a.presentation.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.app4a.R
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.app4a.presentation.main.buttonStatus.SignInError
import com.example.app4a.presentation.main.buttonStatus.SignInSuccess
import com.example.app4a.presentation.main.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_account.*
import org.koin.android.ext.android.inject

class CreateAccountActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mainViewModel.signInLiveData.observe(this, Observer {
            when(it){
                is SignInSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Account Created")
                        .setMessage("Your Account Has Been Created, Please Login")
                        .setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
                SignInError -> {
                    si_mail_error.isVisible = true
                    si_pw_error.isVisible = true
                }
            }
        })

        // Get the Intent that started this activity and extract the string
        // val message = intent.getStringExtra(EXTRA_MESSAGE)

        sign_in_button.setOnClickListener{
            mainViewModel.onClickedSignIn(Email_CAA_edit.text.toString().trim(),Password_CAA_edit.text.toString().trim())
        }

        Email_CAA_edit.addTextChangedListener(SignInTextWatcher);
        Password_CAA_edit.addTextChangedListener(SignInTextWatcher);
    }

    private val SignInTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            sign_in_button.isEnabled = !Email_CAA_edit.text.toString().trim().isEmpty() || !Password_CAA_edit.text.toString().trim().isEmpty()
        }
    };
}