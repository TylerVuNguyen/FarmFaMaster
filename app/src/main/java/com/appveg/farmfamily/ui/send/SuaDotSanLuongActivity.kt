package com.appveg.farmfamily.ui.send

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.*
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.positionSpinner
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*
import java.util.*
import kotlin.collections.ArrayList

class SuaDotSanLuongActivity : AppCompatActivity() {

    val listRau = generateRauData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_dot_san_luong)


        edit_pickDateBD.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    edit_textViewPickStart.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        edit_pickDateEnd.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    edit_textViewPickKT.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }


        //swipemenulistview
        val creator1 = SwipeMenuCreator { menu ->

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
        lv_suaSL.setMenuCreator(creator1)
        lv_suaSL.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        Toast.makeText(this@SuaDotSanLuongActivity, listRau[position].toString(), Toast.LENGTH_SHORT).show()
                    }

                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })



        //SPINER hien thi danh sach rau
        val listRau1 = arrayOf("Rau cải", "Rau ngót ", "Rau xà lách")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listRau1)
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

        //hien thi list view
//action listview
        lv_suaSL.adapter = this?.let { ThemAdapter(it, listRau) }
        lv_suaSL.setOnItemClickListener { adapterView, view, i, l ->
            if (listRau.get(i).rau_id == 1) {
                Toast.makeText(this,"ahihi", Toast.LENGTH_SHORT).show()
            }
        }

        //button cap nhat
        editALLSL.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@SuaDotSanLuongActivity, "Cập nhật thành công",Toast.LENGTH_SHORT).show()
            }

        })

        //buton huy
        deleteEditAllSL.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@SuaDotSanLuongActivity, "Huỷ",Toast.LENGTH_SHORT).show()
            }

        })

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
