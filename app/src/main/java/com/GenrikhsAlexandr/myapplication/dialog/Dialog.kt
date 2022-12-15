package com.GenrikhsAlexandr.myapplication.dialog

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.GenrikhsAlexandr.myapplication.MainActivity
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.account.Account
import com.GenrikhsAlexandr.myapplication.databinding.SignDialogBinding


class Dialog(activity: MainActivity) {

    private val act = activity

    private val acc = Account(act)

    fun dialogSingUp(index: Int) {
        val builder = AlertDialog.Builder(act)
        val binding = SignDialogBinding.inflate(act.layoutInflater)
        builder.setView(binding.root)

        setDialogState(index, binding)

        val dialog = builder.create()

        binding.btSingInuP.setOnClickListener {
            setOnClickSignUpIn(index, binding, dialog)
        }

        binding.btForgetP.setOnClickListener {
            setOnClickForgetP(binding, dialog)
        }


        dialog.show()
    }


    private fun setOnClickForgetP(binding: SignDialogBinding, dialog: AlertDialog?) {
        if(binding.edSignEmailLayout.editText?.text?.isNotEmpty() == true) {
            act.mAuth.sendPasswordResetEmail(binding.edSignEmailLayout.editText?.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(act, R.string.password_recover, Toast.LENGTH_LONG).show()
                    }
                }
            dialog?.dismiss()
        }
        else {
            binding.tvRecoveryP.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSignUpIn(index: Int, binding: SignDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if (index == Constants.SIGN_UP_STATE) {
            acc.signUpWithEmail(binding.edSignEmailLayout.editText?.text.toString(), binding.edPasswordLayout.editText?.text.toString())

        } else {
            acc.signInWithEmail(binding.edSignEmailLayout.editText?.text.toString(), binding.edPasswordLayout.editText?.text.toString())
        }

    }

    private fun setDialogState(index: Int, binding: SignDialogBinding) {
        if (index == Constants.SIGN_UP_STATE) {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            binding.btSingInuP.text = act.resources.getString(R.string.sign_up_action)

        } else {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            binding.btSingInuP.text = act.resources.getString(R.string.sign_in_action)
            binding.btForgetP.visibility = View.VISIBLE
        }

    }


}