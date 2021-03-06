package com.joaquinalvidrez.foodselregio.fragment


import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.joaquinalvidrez.foodselregio.R
import com.joaquinalvidrez.foodselregio.model.User
import kotlinx.android.synthetic.main.fragment_register_dialog.view.*


class RegisterDialogFragment : AppCompatDialogFragment() {
    private lateinit var positiveButton: Button
    private lateinit var user: User
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        user = arguments?.getParcelable(BUNDLE_USER) ?: User()
        dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_register_dialog, null)

        dialogView.edit_text_register_dialog_email.setText(user.email)
        dialogView.edit_text_register_dialog_password.setText(user.password)

        dialogView.edit_text_register_dialog_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                positiveButton.isEnabled = !p0.isNullOrBlank()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        return AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.dialog_title_new_user))
                .setView(dialogView)
                .setPositiveButton(R.string.dialog_button_positive_register, null)
                .setNegativeButton(getString(R.string.dialog_button_negative_cancel), null)
                .create()
    }

    override fun onStart() {
        super.onStart()
        positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        positiveButton.setOnClickListener {
            val db = FirebaseDatabase.getInstance().getReference("Usuarios")
            user.apply {
                password = dialogView.edit_text_register_dialog_password.text.toString()
                email = dialogView.edit_text_register_dialog_email.text.toString()
                userName = dialogView.edit_text_register_dialog_username.text.toString()
            }
            FirebaseAuth
                    .getInstance().createUserWithEmailAndPassword(user.email, user.password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            db.push().setValue(user)
                            Log.e(javaClass.name, "Se pudo registrar")
                            dismiss()
                        } else {
                            Toast.makeText(this@RegisterDialogFragment.context, it.exception?.message, Toast.LENGTH_SHORT).show()
//                            Log.e(javaClass.nombre, it.exception?.message)
                        }
                    }


        }
    }


    companion object {

        private const val BUNDLE_USER = "bundle_user"

        fun newInstance(user: User): RegisterDialogFragment {
            return RegisterDialogFragment().apply {
                arguments = Bundle().apply { putParcelable(BUNDLE_USER, user) }
            }
        }
    }

}

