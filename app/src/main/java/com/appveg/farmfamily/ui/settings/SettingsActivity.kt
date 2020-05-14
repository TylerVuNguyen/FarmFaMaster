package com.appveg.farmfamily.ui.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.appveg.farmfamily.MainActivity
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.login.ChangePasswordActivity
import com.appveg.farmfamily.ui.login.LoginActivity
import com.appveg.farmfamily.ui.login.User
import kotlinx.android.synthetic.main.settings_activity.*
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {

    private val activity = this@SettingsActivity
    private var selected: String? = ""


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

        //spiner language
        var spinnerLanguage = findViewById<Spinner>(R.id.spinner_language)
        var getListLanguage = arrayOf("Tiếng Việt", "Tiếng Anh", "Tiếng Nhật")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, getListLanguage)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        spinnerLanguage.adapter = adapter

        spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                //val item = parent.getItemAtPosition(position) as String
                (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.colorPrimary))
                selected = adapter.getItem(position)
            }
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

    private fun onChangeLanguage (locale: Locale){
        // đối tượng lưu thông tin lích thước tring bay
        var displayMetrics = baseContext.resources.displayMetrics

        //đối tượng cấu hình
        var configuration : Configuration = Configuration()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale)
        }else{
            configuration.locale = locale
        }

        //cài đặt ngôn ngũw
        baseContext.resources.updateConfiguration(configuration,displayMetrics)

        //refesh activity
        var refesh:Intent = Intent(activity,SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }





}