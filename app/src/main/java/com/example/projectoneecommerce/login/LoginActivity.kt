package com.example.projectoneecommerce.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectoneecommerce.dasboard.DashBoardActivity
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.SecuredSharedPreferenceManager
import com.example.projectoneecommerce.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SecuredSharedPreferenceManager.init(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews(){
        checkIsUserAlreadyLoggedIn()
        with(binding){
            btnLogin.setOnClickListener{
                var emailStr = edtEmail.text.toString()
                var passStr = edtPass.text.toString()
                val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

                if(passStr.matches(passwordRegex.toRegex()) && emailStr.matches(emailRegex.toRegex())){
//                    errMsg.text=""
                    Toast.makeText(this@LoginActivity, "Login Successful!!!", Toast.LENGTH_SHORT).show()
                    saveTheUserInfo(emailStr,passStr)
                }
                else{
//                    errMsg.text= getString(R.string.loginErrMsg)
                    val snackbar = Snackbar.make(binding.root, getString(R.string.loginErrMsg), Snackbar.LENGTH_INDEFINITE)
                    val snackbarView = snackbar.view
                    snackbar.setTextColor(ContextCompat.getColor(this@LoginActivity,
                        R.color.errorMsgRed
                    )) // Change the message text color to red
                    snackbar.setActionTextColor(ContextCompat.getColor(this@LoginActivity,
                        R.color.snackbarActionBlue
                    )) // Change the action button text color to blue
                    snackbar.setAction("OK") {
                        // This will dismiss the Snackbar when clicked
                    }
                    snackbar.show()
                }
            }
            noAccountTagd.setOnClickListener{
                val registerIntent = Intent(this@LoginActivity, Register::class.java)
                startActivity(registerIntent)
            }
        }
    }
    private fun saveTheUserInfo(email: String, password: String) {
        SecuredSharedPreferenceManager.saveString(
            SecuredSharedPreferenceManager.USER_EMAIL_SECURED,
            email
        )
        SecuredSharedPreferenceManager.saveString(
            SecuredSharedPreferenceManager.PASSWORD_SECURED,
            password
        )
        SecuredSharedPreferenceManager.saveBooleanAndGetStatus(
            SecuredSharedPreferenceManager.IS_LOGGED_IN_SECURED,
            true
        )
        moveToDashBoardScreen()
    }
    private fun checkIsUserAlreadyLoggedIn() {
        val isLoggedIn =
            SecuredSharedPreferenceManager.getBoolean(SecuredSharedPreferenceManager.IS_LOGGED_IN_SECURED)
        if(isLoggedIn){
            moveToDashBoardScreen()
        }

    }
    private fun moveToDashBoardScreen() {
        startActivity(Intent(this, DashBoardActivity::class.java))
        finish()
    }
}