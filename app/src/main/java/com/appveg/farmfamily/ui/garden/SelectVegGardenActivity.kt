package com.appveg.farmfamily.ui.garden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.activity_add_device_for_garden.*
import kotlinx.android.synthetic.main.activity_select_device_garden.*
import kotlinx.android.synthetic.main.activity_select_veg_garden.*

class SelectVegGardenActivity : AppCompatActivity() {
    private val activity = this@SelectVegGardenActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_veg_garden)
        /*action button*/
        actionButton()
    }
    private fun actionButton() {
        /*event cancel*/
        btn_veg_cancel.setOnClickListener {
            activity.finish()
        }

    }
}
