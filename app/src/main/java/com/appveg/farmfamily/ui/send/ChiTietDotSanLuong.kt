package com.appveg.farmfamily.ui.send

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*


class ChiTietDotSanLuong : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appveg.farmfamily.R.layout.activity_chi_tiet_san_luong)

        val list_view = findViewById<SwipeMenuListView>(R.id.list_view)

        val listRau = generateRauData()

        var  customAdapter = ChiTietAdapter(this, listRau!!)
        list_view!!.adapter = customAdapter


        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.applicationContext
            )
//            // set item background
//            openItem.background = ColorDrawable(
//                Color.rgb(0x00, 0x66,0xff
//                )
//            )
            // set item width
            editItem.width = 100
            // set item title
//            editItem.title = "Open"
//            // set item title fontsize
//            editItem.titleSize = 18
            // set item title font color
//            editItem.titleColor = Color.WHITE

            //set icon
            editItem.setIcon(R.drawable.ic_edit)
            // add to menu
            menu.addMenuItem(editItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.applicationContext
            )
//            // set item background
//            deleteItem.background = ColorDrawable(
//                Color.rgb(
//                    0xF9,
//                    0x3F, 0x25
//                )
//            )
            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set creator
        list_view.setMenuCreator(creator)

        list_view.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override  fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        Toast.makeText(this@ChiTietDotSanLuong,listRau[position].toString(), Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        Toast.makeText(this@ChiTietDotSanLuong,listRau[position].toString(), Toast.LENGTH_LONG).show()
                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })



        list_view.adapter = this?.let { ChiTietAdapter(it, listRau) }
        list_view.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this.applicationContext, " Selected Company is = " + listRau.get(i).rau_name, Toast.LENGTH_SHORT).show()
            if(listRau.get(i).rau_id == 1){
                var  intent: Intent  = Intent(this, ChiTietSanLuongRau::class.java);
                startActivity(intent);
            }

        }

        themDotSanLuong.setOnClickListener {

            var  intent: Intent = Intent(this, ThemDotSanLuong::class.java);
            startActivity(intent);
        }


    }


    private fun generateRauData(): ArrayList<Rau> {
        var result = ArrayList<Rau>()
        var r: Rau = Rau()
        r.rau_id = 1
        r.rau_name = "Đợt 1 "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 2
        r.rau_name = "Đợt 2 "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 3
        r.rau_name = "Đợt 3 "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        r = Rau()
        r.rau_id = 4
        r.rau_name = "Đợt 4 "
        r.rau_photo = R.drawable.kv2
        result.add(r)

        return result
    }}