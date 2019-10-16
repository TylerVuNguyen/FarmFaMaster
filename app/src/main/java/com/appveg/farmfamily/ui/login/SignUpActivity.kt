package com.appveg.farmfamily.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R

class SignUpActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

//        var edt_fullName = findViewById(R.id.fullName) as EditText
//        var edt_userEmailId = findViewById(R.id.userEmailId) as EditText
//        var password = findViewById(R.id.password) as EditText
//        var edt_confirmpassword = findViewById(R.id.confirmPassword) as EditText
        var edt_already_user = findViewById(R.id.already_user) as TextView

        var bt_btSignUp = findViewById(R.id.signUpBtn) as Button


        bt_btSignUp.setOnClickListener{
            //            var userName = edt_fullName.text
//            var email = edt_userEmailId.text
//            var pass = password.text
//            var cofirmpass = edt_confirmpassword.text

            val intent: Intent = Intent(this, com.appveg.farmfamily.MainActivity::class.java)
            startActivity(intent)
        }

        edt_already_user.setOnClickListener{
            val intent: Intent = Intent(this, com.appveg.farmfamily.ui.login.LoginActivity::class.java)
            startActivity(intent)
        }

    }
}