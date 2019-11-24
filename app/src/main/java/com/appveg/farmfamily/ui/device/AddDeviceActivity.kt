package com.appveg.farmfamily.ui.device

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*

class AddDeviceActivity : AppCompatActivity() {
    var activity = this@AddDeviceActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        var nameDevice = findViewById(R.id.nameDevice) as EditText
        var decript_device = findViewById(R.id.decript_device) as EditText

        var btn_device_back = findViewById(R.id.btn_device_back) as Button
        btn_device_back.setOnClickListener {
            var intent:Intent = Intent(activity, DeviceFragment::class.java)
            startActivity(intent)
        }
    }

}
