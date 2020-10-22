package com.somya.foodapp.activity


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.somya.foodapp.R

class MainActivity : AppCompatActivity() {
    lateinit var etMobileNumber:EditText
    lateinit var etPassword:EditText
    lateinit var btnLogin:Button
    lateinit var txtForgotPassword:TextView
    lateinit var txtSignUp:TextView
    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.login_page_layout)
        title="Login"

        etMobileNumber=findViewById(R.id.etMobileNumberLoginPage)
        etPassword=findViewById(R.id.etEnterPasswordLoginPage)
        btnLogin=findViewById(R.id.btnLoginLoginPage)
        txtForgotPassword=findViewById(R.id.txtForgotPasswordLoginPage)
        txtSignUp=findViewById(R.id.txtSignUpLoginPage)

btnLogin.setOnClickListener {
    val intent=Intent(this@MainActivity,
        MainDisplayScreenDishes::class.java)
    startActivity(intent)
}
        txtSignUp.setOnClickListener {
            val intent=Intent(this@MainActivity,
                LoginRegisterActivity::class.java)
            startActivity(intent)
        }


}
}