package com.appveg.farmfamily.ui.send

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.*
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.pickDateBD
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.pickDateKT
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.positionSpinner
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.textViewPickKT
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.textViewPickStart
import kotlinx.android.synthetic.main.layoutlistview_chitietdot_sanluong.*
import java.util.*
import kotlin.collections.ArrayList

class ThemDotSanLuongActivity : AppCompatActivity() {

    val listVeg = generateRauData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_dot_san_luong)


        //calendar laays ngay hien tai
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        //button clock to show DatePickDialog
//        pickDateBD.setOnClickListener {
//            val dBD = DatePickerDialog(
//                this,
//                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                    //set to textView
//                    textViewPick.setText("" + day + "/" + month + "/" + year)
//                }, year, month, day)
//            dBD.show()
//
//        }



        pickDateBD.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    textViewPickStart.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        pickDateKT.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    textViewPickKT.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        //spinner hien thi danh sach rau
        val listRau = arrayOf("Rau cải", "Rau ngót ", "Rau xà lách")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listRau)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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
                // val item = parent.getItemAtPosition(position) as String
                val item = adapter.getItem(position)
            }
        }


        //swipemenulistview
        val creator2 = SwipeMenuCreator { menu ->

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.applicationContext
            )
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        lv_themSL.setMenuCreator(creator2)
        lv_themSL.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> { Toast.makeText(
                        this@ThemDotSanLuongActivity, listVeg[position].toString(), Toast.LENGTH_LONG).show()
                    }

                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })

        //hien thi list view
//action listview

//        var listRau1 = generateRauData()

        lv_themSL.adapter = this?.let { ThemAdapter(it, listVeg) }

        lv_themSL.setOnItemClickListener { adapterView, view, i, l ->
            if (listVeg.get(i).rau_id == 1) {
                Toast.makeText(this,"ahihi", Toast.LENGTH_SHORT).show()
            }

        }




        //button them all san luong
        addALLSL.setOnClickListener (object  : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@ThemDotSanLuongActivity, "Thêm thành công", Toast.LENGTH_SHORT).show()
            }

        })

        //button them all san luong
        deleteAllSL.setOnClickListener (object  : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@ThemDotSanLuongActivity, "Huỷ", Toast.LENGTH_SHORT).show()
            }

        })


    } //ket thuc fun onCreate

    fun getTenRau(): String{
        var tenRau : String
        var soluongRau : Int

        var num = findViewById(R.id.nhapSoLuongRau) as EditText
        num.text =

        return num.toString()

    }


    //fun lis rau
    fun generateRauData(): ArrayList<Rau> {
        var result = ArrayList<Rau>()
        var r: Rau = Rau()
        r.rau_id = 1
        r.rau_name = "Rau cải "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 2
        r.rau_name = "Rau xà "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 3
        r.rau_name = "Rau ngò "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 4
        r.rau_name = "Hành "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        return result
    }


}
