package com.joaquinalvidrez.foodselregio.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.joaquinalvidrez.foodselregio.R
import com.joaquinalvidrez.foodselregio.fragment.RegisterDialogFragment
import com.joaquinalvidrez.foodselregio.model.User
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_EMAIL = "key_email"
const val EXTRA_PASSWORD = "key_password"

class LoginActivity : AppCompatActivity() {

    private val email: String get() = edit_text_login_email.text.toString()
    private val password: String get() = edit_text_login_password.text.toString()
    private val areFieldsFilled: Boolean
        get() = !edit_text_login_email.text.isNullOrEmpty() && !edit_text_login_password.text.isNullOrEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        FirebaseAuth.getInstance().addAuthStateListener {
            val user = it.currentUser
            if (user == null) {

            } else {
                showMessage("Signed-in as ${user.email}")
            }

        }


        button_login_sign_in.apply {
            isEnabled = areFieldsFilled
            setOnClickListener {
                FirebaseAuth
                        .getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showMessage("success")

                            } else {
//                                showMessage(context.getString(R.string.message_email_or_password_incorrect))
                                showMessage(it.exception?.message!!)

                            }
                        }
            }
        }

        edit_text_login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                button_login_sign_in.isEnabled = areFieldsFilled
                button_login_register.isEnabled = areFieldsFilled
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        edit_text_login_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                button_login_sign_in.isEnabled = areFieldsFilled
                button_login_register.isEnabled = areFieldsFilled
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        button_login_register.apply {
            setOnClickListener {
                RegisterDialogFragment.newInstance(User(email = email, password = password))
                        .show(supportFragmentManager, "")
//                startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java).apply {
//                    putExtra(EXTRA_EMAIL, email)
//                    putExtra(EXTRA_PASSWORD, password)
//                })
            }
            isEnabled = areFieldsFilled
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(main_layout, message, Snackbar.LENGTH_SHORT).show()
    }
}
