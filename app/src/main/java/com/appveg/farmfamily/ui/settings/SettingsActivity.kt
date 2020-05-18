package com.appveg.farmfamily.ui.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.constant.Libs
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.login.ChangePasswordActivity
import com.appveg.farmfamily.ui.login.LoginActivity
import com.appveg.farmfamily.ui.login.User
import kotlinx.android.synthetic.main.custom_dialog_lang.view.*
import kotlinx.android.synthetic.main.settings_activity.*
import kotlinx.android.synthetic.main.settings_activity.view.*
import kotlin.collections.ArrayList

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
            dialogBuilder.setMessage(getString(R.string.delete_title_all_vi))
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton(
                    getString(R.string.yes_vi),
                    DialogInterface.OnClickListener { dialog, id ->
                        updateStatusAfterLogOut()
                    })
                // negative button text and action
                .setNegativeButton(
                    getString(R.string.quit_vi),
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle(getString(R.string.log_out_system_vi))
            // show alert dialog
            alert.show()
        }

        changePass.setOnClickListener {
            val intent: Intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        view_change_lang.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val dialogView = inflater.inflate(R.layout.custom_dialog_lang, null)
            var libs = Libs()

            val currentLang = libs.loadLocale(activity)
            when (currentLang) {
                "vi" -> {
                    dialogView.radio_vi.isChecked = true
                    dialogView.radio_en.isChecked = false
                    dialogView.radio_ja.isChecked = false
                }
                "en" -> {
                    dialogView.radio_vi.isChecked = false
                    dialogView.radio_en.isChecked = true
                    dialogView.radio_ja.isChecked = false
                }
                else -> {
                    dialogView.radio_vi.isChecked = false
                    dialogView.radio_en.isChecked = false
                    dialogView.radio_ja.isChecked = true
                }
            }
            var radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
            var lang = "en"

            radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, checkedId ->
                when (checkedId) {
                    R.id.radio_vi -> {
                        lang = "vi"
                    }
                    R.id.radio_en -> {
                        lang = "en"
                    }
                    R.id.radio_ja -> {
                        lang = "ja"
                    }
                }
            })

            dialogBuilder.setView(dialogView)
            // positive button text and action
            dialogBuilder.setPositiveButton(
                getString(R.string.ok_vi),
                DialogInterface.OnClickListener { dialog, id ->
                    libs.changeLang(lang, activity)
                })
                // negative button text and action
                .setNegativeButton(
                    getString(R.string.cancel_vi),
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle(getString(R.string.choose_lang))
            // show alert dialog
            alert.show()
        }


    }

    private fun updateStatusAfterLogOut() {
        database = Database(activity)
        users = database.getAllUser()
        if (!users.isNullOrEmpty()) {
            for (item in 0 until users.size) {
                if (2 == users[item].status) {
                    var id = database.updateStatusByUserNameEmail(users[item].email!!, 0)
                }
            }
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}