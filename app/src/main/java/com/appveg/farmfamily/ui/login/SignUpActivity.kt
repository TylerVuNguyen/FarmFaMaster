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
    private lateinit var errorCommon: TextView

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


        //text view
        errorCommon = findViewById(R.id.errorCommon)

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

        val userSelectedID : Int = radioGenderGroup.checkedRadioButtonId
        val userRadioGenderGroup = findViewById(userSelectedID) as RadioButton
        val userGenderGroup = userRadioGenderGroup.text.toString().trim()



        if (userFullName.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.error = getString(R.string.error_message_email)
        }else if (userFullName.isEmpty()) {
            fullName.error = getString(R.string.error_message_name)
        }else if (userPassword.isEmpty()){
            password.error = getString(R.string.error_message_password)
        }else if (userConfirmPass.isEmpty()){
            password.error = getString(R.string.error_message_confirm_password)
        }

        if (!userPassword.contentEquals(userConfirmPass)) {
            password.error = getString(R.string.error_password_match)
            confirmPassword.error = getString(R.string.error_password_match)
        }

        // if checked all then add user
        // new user

        var user : User = User(null,null,userFullName,userEmail,userPassword,userGenderGroup,1,"admin",null)
        if(user != null){
            try {
                database!!.addUser(user)
                errorCommon.text = getString(R.string.success_message)
            }catch (e : Exception){
                Log.d("AAA",e.message)
            }

        }

    }
}