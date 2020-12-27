package com.example.app4a.presentation.textWatcher

import android.text.Editable

class LoginTW(s: Editable?) : android.text.TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        val regex = "[a-zA-Z0-9]{1,16}@[a-zA-Z0-9]{4,8}.[a-zA-Z]{3}".toRegex()
        assertTrue(regex.containsMatchIn(s.toString().trim()))
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TODO("Not yet implemented")
    }
}