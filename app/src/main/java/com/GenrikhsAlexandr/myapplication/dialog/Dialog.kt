package com.GenrikhsAlexandr.myapplication.dialog

import android.app.AlertDialog
import com.GenrikhsAlexandr.myapplication.MainActivity
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.account.Account
import com.GenrikhsAlexandr.myapplication.databinding.SignDialogBinding
import com.google.android.material.textfield.TextInputLayout


class Dialog(activity: MainActivity) {

    private val act = activity

    private val acc = Account(act)

    fun dialogSingUp(index: Int) {
        val builder = AlertDialog.Builder(act)
        val binding = SignDialogBinding.inflate(act.layoutInflater)
        builder.setView(binding.root)
        if (index == Constants.SIGN_UP_STATE) {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            binding.btSingInuP.text = act.resources.getString(R.string.sign_up_action)

        } else {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            binding.btSingInuP.text = act.resources.getString(R.string.sign_in_action)
            binding.btForgetP.text = act.resources.getString(R.string.forget_password)
        }
        val dialog = builder.create()

        binding.btSingInuP.setOnClickListener {
            dialog.dismiss()
            if (index == Constants.SIGN_UP_STATE) {
                acc.signUpWithEmail(binding.edSignEmailLayout.editText?.text.toString(), binding.edPasswordLayout.editText?.text.toString())

            } else {
                acc.signInWithEmail(binding.edSignEmailLayout.editText?.text.toString(), binding.edPasswordLayout.editText?.text.toString())
            }

        }


        dialog.show()
    }
}