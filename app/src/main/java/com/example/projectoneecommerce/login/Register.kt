package com.example.projectoneecommerce.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectoneecommerce.R
import com.example.projectoneecommerce.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews(){
        with(binding){
            btnRegister.setOnClickListener{
                var fullNameStr = edtfullNameRegister.text.toString()
                var mobileNoStr = edtMobileNumberRegister.text.toString()
                var emailIdStr = edtEmailIdRegister.text.toString()
                var passwordStr = edtPasswordRegister.text.toString()

                val fullNameRegex = "^[a-zA-Z\\s'-]+$"
                val mobileNoRegex = "^\\d{10}$"
                val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

                if (fullNameStr.matches(fullNameRegex.toRegex()) &&
                    mobileNoStr.matches(mobileNoRegex.toRegex()) &&
                    emailIdStr.matches(emailRegex.toRegex()) &&
                    passwordStr.matches(passwordRegex.toRegex())) {

                    errMsgRegister.text = ""
                    Toast.makeText(this@Register, "Registration Successful!!!", Toast.LENGTH_SHORT).show()
                } else {
                    errMsgRegister.text = getString(R.string.registrationErrMsg)
                }
            }
        }
    }
}