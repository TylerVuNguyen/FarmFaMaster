package com.appveg.farmfamily.ui.garden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_select_device_garden.*

class SelectDeviceGardenActivity : AppCompatActivity() {
    private val activity = this@SelectDeviceGardenActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_device_garden)
        /*action button*/
        actionButton()
    }
    private fun actionButton() {
        /*event cancel*/
        btn_device_cancel.setOnClickListener {
            activity.finish()
        }

    }
}
