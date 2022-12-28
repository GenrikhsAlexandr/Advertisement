package com.GenrikhsAlexandr.myapplication.presitation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.GenrikhsAlexandr.myapplication.databinding.ActivityNewBinding
import com.GenrikhsAlexandr.myapplication.dialog.DialogSpinner
import com.GenrikhsAlexandr.myapplication.utils.City

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val listCountry = City.getAllCountry(this)
        val dialog = DialogSpinner()
        dialog.showDialogSpinner(this, listCountry)
    }
}