package com.GenrikhsAlexandr.myapplication.account

import android.widget.Toast
import com.GenrikhsAlexandr.myapplication.MainActivity
import com.GenrikhsAlexandr.myapplication.R
import com.google.firebase.auth.FirebaseUser

class Account(activity: MainActivity) {
    private val act = activity
    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    sendEmailVerification(task.result?.user!!)
                } else {
                    Toast.makeText(act,
                        act.resources.getString(R.string.sign_up_error),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(act,
                    act.resources.getString(R.string.email_verification),
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(act,
                    act.resources.getString(R.string.email_verification_error),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}


