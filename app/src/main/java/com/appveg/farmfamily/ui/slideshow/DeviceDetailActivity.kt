package com.appveg.farmfamily.ui.slideshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.appveg.farmfamily.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_device_detail.*

class DeviceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail)

        //icon them
        val fab1: FloatingActionButton = findViewById(R.id.fab1)
        fab1.setOnClickListener { view ->
            //            Snackbar.make(view, "Them ", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()

            var  intent: Intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"ahihi", Toast.LENGTH_SHORT).show()
        }


       editDevice.setOnClickListener{
           var  intent: Intent = Intent(this, EditDeviceActivity::class.java)
           startActivity(intent)
       }





    }
}
