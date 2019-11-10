package com.appveg.farmfamily.ui.slideshow

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*

class AddDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        var selectDevice = findViewById(R.id.selectDevice) as Spinner

        var selectGarden = findViewById(R.id.selectGarden) as Spinner

        var txtDonVi = findViewById(R.id.txtDonVi) as TextView
        var edtDonVi = findViewById(R.id.edtDonVi) as EditText

        //spinner hien thi thiet bi
        val listThietBi = arrayOf("Quạt","Cảm biến", "Máy bơm", "Đo pH")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listThietBi)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectDevice.adapter = adapter

        selectDevice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String
                val item = adapter.getItem(position)
                if(position == 1){
                    Log.d("test1",position.toString())
                    txtDonVi.visibility = View.VISIBLE
                    edtDonVi.visibility = View.VISIBLE
                }else{
                    txtDonVi.visibility = View.GONE
                    edtDonVi.visibility = View.GONE
                }

            }
        }



        //spinner hien thi Khu Vuon
        val listGarden = arrayOf("Khu vườn 1", "Khu vườn 2", "Khu vườn 3")

        val adapterGarden = ArrayAdapter(this, android.R.layout.simple_spinner_item, listGarden)
        adapterGarden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectGarden.adapter = adapterGarden

        selectGarden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String
                val item = adapterGarden.getItem(position)
            }
        }


    }



}
