package com.appveg.farmfamily.ui.device

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.appveg.farmfamily.R

class EditDeviceActivity : AppCompatActivity() {
    var activity = this@EditDeviceActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_device)

        var btn_device_back_edit = findViewById(R.id.btn_device_back) as Button
        btn_device_back_edit.setOnClickListener {
            var intent: Intent = Intent(activity, DeviceFragment::class.java)
            startActivity(intent)
        }
    }
}
