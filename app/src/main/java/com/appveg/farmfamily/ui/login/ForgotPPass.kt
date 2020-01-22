package com.appveg.farmfamily.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R

class ForgotPPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpass)

//        var registered_emailid = findViewById(R.id.registered_emailid) as EditText
        var btSubmit = findViewById<Button>(R.id.forgot_button)
        var btBack = findViewById<Button>(R.id.backToLoginBtn)

        btSubmit.setOnClickListener{
            //            var email = registered_emailid.text
            val intent: Intent = Intent(this, com.appveg.farmfamily.MainActivity::class.java)
            startActivity(intent)
        }
        btBack.setOnClickListener{
            val intent: Intent = Intent(this, com.appveg.farmfamily.ui.login.LoginActivity::class.java)
            startActivity(intent)
        }


    }
}