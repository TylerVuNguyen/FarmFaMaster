package com.appveg.farmfamily.ui.send

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*

class chiTietSanLuong : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_san_luong)

        val listRau = generateRauData()

        list_view.adapter = this?.let { ChiTietAdapter(it, listRau) }

        list_view.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this.applicationContext, " Selected Company is = " + listRau.get(i).rau_name, Toast.LENGTH_SHORT).show()


        }
    }
    private fun generateRauData(): ArrayList<Rau> {
        var result = ArrayList<Rau>()
        var r: Rau = Rau()
        r.rau_id = 1
        r.rau_name = "Rau cải "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 1
        r.rau_name = "Rau xà "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 1
        r.rau_name = "Rau ngò "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 1
        r.rau_name = "Hành "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        return result
    }
}
