package com.joaquinalvidrez.foodselregio.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.joaquinalvidrez.foodselregio.R
import com.joaquinalvidrez.foodselregio.model.User
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val db = FirebaseDatabase.getInstance().getReference("Users")
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val password = intent.getStringExtra(EXTRA_PASSWORD)
        val user = User(email = email, password = password)
        button_registration_register.setOnClickListener {
            FirebaseAuth
                    .getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            user.userName = edit_text_register_username.text.toString()
                            db.push().setValue(user)
                            Toast.makeText(this@RegistrationActivity, "Éxito", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@RegistrationActivity, it.exception?.message, Toast.LENGTH_SHORT).show()

                        }
                    }
        }
    }
}
