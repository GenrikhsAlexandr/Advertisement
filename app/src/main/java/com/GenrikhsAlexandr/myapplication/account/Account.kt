package com.GenrikhsAlexandr.myapplication.account

import android.util.Log
import android.widget.Toast
import com.GenrikhsAlexandr.myapplication.presitation.MainActivity
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.constants.ConstansError
import com.google.firebase.auth.*

class Account(activity: MainActivity) {

    private val act = activity

    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        sendEmailVerification(task.result?.user!!)
                        act.uiUpdate(task.result?.user)
                    } else {
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            val exception = task.exception as FirebaseAuthUserCollisionException
                            if (exception.errorCode == ConstansError.ERROR_EMAIL_ALREADY_IN_USE) {
                                Toast.makeText(act, ConstansError.ERROR_EMAIL_ALREADY_IN_USE,
                                    Toast.LENGTH_SHORT).show()
                            }
                        } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            val exception =
                                task.exception as FirebaseAuthInvalidCredentialsException
                            if (exception.errorCode == ConstansError.ERROR_INVALID_EMAIL) {
                                Toast.makeText(act, ConstansError.ERROR_INVALID_EMAIL,
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                        /*  if (task.exception is FirebaseAuthWeakPasswordException) {
                              val exception =
                                  task.exception as FirebaseAuthWeakPasswordException
                              if (exception.errorCode == ConstansError.ERROR_WEAK_PASSWORD) {
                                  Toast.makeText(act, ConstansError.ERROR_WEAK_PASSWORD,
                                      Toast.LENGTH_SHORT).show()
                              }
                          }*/
                    }
                }
        }
    }

    fun signInWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    act.uiUpdate(task.result?.user)
                } else {
                    Log.d("MyLog", "Exception: ${task.exception}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        val exception = task.exception as FirebaseAuthInvalidCredentialsException
                        Log.d("MyLog", "ExceptionCode: ${exception.errorCode}")
                        if (exception.errorCode == ConstansError.ERROR_WRONG_PASSWORD) {
                            Toast.makeText(act, ConstansError.ERROR_WRONG_PASSWORD,
                                Toast.LENGTH_SHORT).show()
                        }
                        if (exception.errorCode == ConstansError.ERROR_INVALID_EMAIL){
                            Toast.makeText(act, ConstansError.ERROR_INVALID_EMAIL,
                                Toast.LENGTH_SHORT).show()
                        }
                    } else if (task.exception is FirebaseAuthInvalidUserException) {
                        val exception = task.exception as FirebaseAuthInvalidUserException
                        if (exception.errorCode == ConstansError.ERROR_USER_NOT_FOUND) {
                            Toast.makeText(act, ConstansError.ERROR_USER_NOT_FOUND,
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    private fun sendEmailVerification(user: FirebaseUser) {
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


// Log.d("MyLog", "Exception: ${exception.errorCode}")
//aleksandr.genrikhs@yandex.ru


