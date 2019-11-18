package com.appveg.farmfamily.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import kotlinx.android.synthetic.main.signup.*

class SignUpActivity  : AppCompatActivity() {
    private val activity = this@SignUpActivity

    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText

    private lateinit var already_user: TextView

    private lateinit var btSignup: Button

    private lateinit var radioGenderGroup : RadioGroup



    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        //edit text
        fullName = findViewById(R.id.fullName)
        email = findViewById(R.id.userEmailId)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)

        //button
        already_user = findViewById(R.id.already_user)
        btSignup = findViewById(R.id.signUpBtn)

        //radio button
        radioGenderGroup = findViewById(R.id.radioGenderGroup)




        //buotn listener sig up
        btSignup.setOnClickListener{
            verifyFromSQLite()
        }

        //button lister already user
        already_user.setOnClickListener{
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite(){
        database = Database(activity)
        val userFullName = fullName.text.toString().trim()
        val userEmail = email.text.toString().trim()
        val userPassword = password.text.toString().trim()
        val userConfirmPass = confirmPassword.text.toString().trim()

        // radio button gender
        val userSelectedID : Int = radioGenderGroup.checkedRadioButtonId
        val userRadioGenderGroup = findViewById(userSelectedID) as RadioButton
        val userGenderGroup = userRadioGenderGroup.text.toString().trim()

        // validate
        var checkFullName = checkFullName(userFullName)
        var checkEmail = checkEmail(userEmail)
        var checkPassword = checkPassword(userPassword)
        var checkConfirmPass = checkConfirmPassword(userConfirmPass)

        var checkMatchPass: Boolean = false
        if(checkPassword && checkConfirmPass){
            checkMatchPass = checkMatchPass(userPassword,userConfirmPass)
        }

        if(checkFullName && checkEmail && checkConfirmPass && checkPassword && checkMatchPass){
            // if checked all then add user
            // new user
            var user : User = User(null,null,userFullName,userEmail,userPassword,userGenderGroup,1,"admin",null)
            if(user != null){
                try {
                    database!!.addUser(user)
                    Toast.makeText(applicationContext,getString(R.string.sign_up_success),
                        Toast.LENGTH_LONG).show()
                }catch (e : Exception){
                    Log.d("AAA",e.message)
                }

            }
        }


    }

    /**
     * This method is to email
     */
    private fun checkEmail(check: String) : Boolean {
        if (check.isEmpty()) {
            email.error = getString(R.string.error_empty_common)
            return false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(check).matches()){
            email.error = getString(R.string.error_invalid_email)
            return false
        }
        return true
    }
    /**
     * This method is to full name
     */
    private fun checkFullName(check: String) : Boolean {
        if (check.isEmpty()) {
            fullName.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }
    /**
     * This method is to password
     */
    private fun checkPassword(check: String) : Boolean {
        if (check.isEmpty()) {
            password.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }
    /**
     * This method is to confirmPassword
     */
    private fun checkConfirmPassword(check: String) : Boolean {
        if (check.isEmpty()) {
            confirmPassword.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }
    /**
     * This method is to checkMatchPass
     */
    private fun checkMatchPass(check1: String,check2: String) : Boolean {
        if (!check1.equals(check2)) {
            confirmPassword.error = getString(R.string.error_match_password)
            return false
        }
        return true
    }
}