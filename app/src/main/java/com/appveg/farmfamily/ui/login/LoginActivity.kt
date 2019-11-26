package com.appveg.farmfamily.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database

class LoginActivity  : AppCompatActivity() {
    private val activity = this@LoginActivity

    private lateinit var edtUserNameEmail: EditText
    private lateinit var editPass: EditText

    private lateinit var txtforgot: TextView

    private lateinit var btLogin: Button
    private lateinit var btSignup: Button

    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //edit text
        edtUserNameEmail = findViewById(R.id.editUserName)
        editPass = findViewById(R.id.editPass)

        //button
        txtforgot = findViewById(R.id.txtforgot)
        btLogin = findViewById(R.id.btLogin)
        btSignup = findViewById(R.id.btSignup)



        btLogin.setOnClickListener{
          verifyFromSQLite()
//            val intent: Intent = Intent(this, com.appveg.farmfamily.MainActivity::class.java)
//            startActivity(intent)
        }

        btSignup.setOnClickListener{
            val intent: Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        txtforgot.setOnClickListener{
            val intent: Intent = Intent(this, ForgotPPass::class.java)
            startActivity(intent)
        }




    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite(){
        database = Database(activity)
        val userNameEmail = edtUserNameEmail.text.toString().trim()
        val userNamePass = editPass.text.toString().trim()

        checkEmail(userNameEmail)
        checkPassword(userNamePass)

        if (database!!.checkUser(edtUserNameEmail!!.text.toString().trim { it <= ' ' }, editPass!!.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, com.appveg.farmfamily.MainActivity::class.java)
            // accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        }else{
            Toast.makeText(applicationContext,getString(R.string.error_username_pass_invalid),
                Toast.LENGTH_LONG).show()
        }

    }
    /**
     * This method is to check email
     */
    private fun checkEmail(check: String) : Boolean {
        if (check.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(check).matches()) {
            edtUserNameEmail.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }
    /**
     * This method is to password
     */
    private fun checkPassword(check: String) : Boolean {
        if (check.isEmpty()) {
            editPass.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        edtUserNameEmail!!.text = null
        editPass!!.text = null
    }
}