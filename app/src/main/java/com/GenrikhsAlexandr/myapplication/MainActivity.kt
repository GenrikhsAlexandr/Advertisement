package com.GenrikhsAlexandr.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.GenrikhsAlexandr.myapplication.databinding.ActivityMainBinding
import com.GenrikhsAlexandr.myapplication.dialog.Constants
import com.GenrikhsAlexandr.myapplication.dialog.Dialog
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  lateinit var binding: ActivityMainBinding

  val dialog = Dialog(this)

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
      binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.mainContent.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener (this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.id_my_ads ->{
                Toast.makeText(this, "my ads", Toast.LENGTH_LONG).show()

            }
            R.id.id_car ->{
                Toast.makeText(this, "car", Toast.LENGTH_LONG).show()

            }
            R.id.id_pc ->{
                Toast.makeText(this, "pc", Toast.LENGTH_LONG).show()

            }
            R.id.id_smart ->{
                Toast.makeText(this, "smart", Toast.LENGTH_LONG).show()

            }
            R.id.id_dm ->{
                Toast.makeText(this, "dm", Toast.LENGTH_LONG).show()

            }
            R.id.id_sign_in ->{
                dialog.dialogSingUp(Constants.SIGN_IN_STATE)

            }
            R.id.id_sign_up ->{
                dialog.dialogSingUp(Constants.SIGN_UP_STATE)

            }
            R.id.id_sign_out ->{
                Toast.makeText(this, "sign_out", Toast.LENGTH_LONG).show()

            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}