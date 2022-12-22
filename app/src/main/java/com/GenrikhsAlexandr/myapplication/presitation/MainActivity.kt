package com.GenrikhsAlexandr.myapplication.presitation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.GenrikhsAlexandr.myapplication.R
import com.GenrikhsAlexandr.myapplication.databinding.ActivityMainBinding
import com.GenrikhsAlexandr.myapplication.constants.ConstantsDialog
import com.GenrikhsAlexandr.myapplication.dialog.Dialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var tvAccount: TextView

    private lateinit var binding: ActivityMainBinding

    private val dialog = Dialog(this)

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.new_ads) {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ConstantsDialog.SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    dialog.accGoogle.signInFirebaseWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {

            }

            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)

    }


    private fun init() {
        setSupportActionBar(binding.mainContent.toolbar)
        Log.d("MyLog", "SSSS")
        val toggle = ActionBarDrawerToggle(this,
            binding.drawerLayout,
            binding.mainContent.toolbar,
            R.string.open,
            R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_my_ads -> {
                Toast.makeText(this, "my ads", Toast.LENGTH_LONG).show()

            }
            R.id.id_car -> {
                Toast.makeText(this, "car", Toast.LENGTH_LONG).show()

            }
            R.id.id_pc -> {
                Toast.makeText(this, "pc", Toast.LENGTH_LONG).show()

            }
            R.id.id_smart -> {
                Toast.makeText(this, "smart", Toast.LENGTH_LONG).show()

            }
            R.id.id_dm -> {
                Toast.makeText(this, "dm", Toast.LENGTH_LONG).show()

            }
            R.id.id_sign_in -> {
                dialog.dialogSingUp(ConstantsDialog.SIGN_IN_STATE)

            }
            R.id.id_sign_up -> {
                dialog.dialogSingUp(ConstantsDialog.SIGN_UP_STATE)

            }
            R.id.id_sign_out -> {
                uiUpdate(null)
                mAuth.signOut()
                dialog.accGoogle.signOutGoogle()

            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) {
            resources.getString(R.string.no_registration)
        } else {
            user.email
        }
    }
}