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

    private var selected: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        var nameDevice = findViewById(R.id.nameDevice) as EditText

        var btn_device_back = findViewById(R.id.btn_device_back) as Button
        btn_device_back.setOnClickListener {
            var intent:Intent = Intent(activity, DeviceFragment::class.java)
            startActivity(intent)
        }



        //spinner hien thi danh sach rau
        val listSensor = arrayOf("Cảm biến", "Quạt", "Motor")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSensor)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        positionSpinner.adapter = adapter

        positionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // either one will work as well
                //val item = parent.getItemAtPosition(position) as String
                selected = adapter.getItem(position)
            }
        }
    }

}
