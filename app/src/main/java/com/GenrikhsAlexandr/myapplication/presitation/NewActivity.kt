package com.GenrikhsAlexandr.myapplication.presitation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityNewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}