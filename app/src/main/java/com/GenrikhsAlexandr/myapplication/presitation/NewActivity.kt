package com.GenrikhsAlexandr.myapplication.presitation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.databinding.ActivityNewBinding
import com.GenrikhsAlexandr.myapplication.utils.City

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, City.getAllCountry(this))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCountry.adapter = adapter
    }
}