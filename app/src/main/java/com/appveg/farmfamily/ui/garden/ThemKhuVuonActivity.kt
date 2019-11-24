package com.appveg.farmfamily.ui.garden

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_them_khu_vuon.*
import java.util.*

class ThemKhuVuonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_khu_vuon)

        pickDateBD_KV.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    textViewPickBD_KV.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        btn_back.setOnClickListener {
            var intent: Intent = Intent(this@ThemKhuVuonActivity, GalleryFragment::class.java)
            startActivity(intent)
        }

        addKV_btn_add.setOnClickListener {
            var intent: Intent = Intent(this@ThemKhuVuonActivity, GalleryFragment::class.java)
            startActivity(intent)
        }


    }
}