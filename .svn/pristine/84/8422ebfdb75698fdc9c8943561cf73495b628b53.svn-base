package com.appveg.farmfamily.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R

class LoginActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

//        var edt_edtUserName = findViewById(R.id.edtUserName) as EditText
//        var edt_edtPass = findViewById(R.id.edtPass) as EditText
//        var checkbox_remember = findViewById(R.id.remember) as CheckBox

        var txt_txtforgot = findViewById(R.id.txtforgot) as TextView
        var bt_btLogin = findViewById(R.id.btLogin) as Button
        var bt_btSignUp = findViewById(R.id.btSignup) as Button

        bt_btLogin.setOnClickListener{
            //            var userName = edt_edtUserName.text
//            var pass = edt_edtPass.text

            val intent: Intent = Intent(this, com.appveg.farmfamily.MainActivity::class.java)
            startActivity(intent)
        }

        bt_btSignUp.setOnClickListener{
            val intent: Intent = Intent(this, com.appveg.farmfamily.ui.login.SignUpActivity::class.java)
            startActivity(intent)
        }

        txt_txtforgot.setOnClickListener{
            val intent: Intent = Intent(this, com.appveg.farmfamily.ui.login.ForgotPPass::class.java)
            startActivity(intent)
        }
    }
}