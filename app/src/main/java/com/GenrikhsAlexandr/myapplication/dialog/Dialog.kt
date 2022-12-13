package com.GenrikhsAlexandr.myapplication.dialog

import android.app.AlertDialog
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
        if (index == Constants.SIGN_UP_STATE) {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_up)
            binding.btSingInuP.text = act.resources.getString(R.string.sign_in_action)

        } else {
            binding.tvSignTitle.text = act.resources.getString(R.string.ac_sign_in)
            binding.btSingInuP.text = act.resources.getString(R.string.ac_sign_in)
            binding.btForgetP.text = act.resources.getString(R.string.forget_password)
        }

        binding.btSingInuP.setOnClickListener {
            if (index == Constants.SIGN_UP_STATE) {
                acc.signUpWithEmail(binding.edSignEmail.toString(), binding.edPassword.toString())

            } else
            {
            }

        }

        builder.setView(binding.root)
        builder.show()
    }
}