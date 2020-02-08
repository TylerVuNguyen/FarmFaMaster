package com.appveg.farmfamily.ui.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.login.ChangePasswordActivity
import com.appveg.farmfamily.ui.login.LoginActivity
import com.appveg.farmfamily.ui.login.User
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : AppCompatActivity() {

    private val activity = this@SettingsActivity

    private lateinit var database: Database
    var users: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.settings, SettingsFragment())
//            .commit()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // log out account
        log_out.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(activity)

            // set message of alert dialog
            dialogBuilder.setMessage("Bạn có chắc chắn muốn đăng xuất không ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id -> updateStatusAfterLogOut()
                })
                // negative button text and action
                .setNegativeButton("Không", DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Đăng xuất")
            // show alert dialog
            alert.show()
        }

        changePass.setOnClickListener {
            val intent: Intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    //    class SettingsFragment : PreferenceFragmentCompat() {
//        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//            setPreferencesFromResource(R.xml.root_preferences, rootKey)
//        }
//    }
    private fun updateStatusAfterLogOut() {
        database = Database(activity)
        users = database.getAllUser()
        if(!users.isNullOrEmpty()){
            for (item in 0 until users.size ){
                if(2 == users[item].status){
                    var id = database.updateStatusByUserNameEmail(users[item].email!!,0)
                }
            }
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}