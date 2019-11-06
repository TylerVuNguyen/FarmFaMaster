package com.appveg.farmfamily.ui.send

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*
import android.widget.Toast


class ChiTietDotSanLuongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_san_luong)

//action listview
        val listRau = generateDotRauData()

        list_view.adapter = this?.let { ChiTietAdapter(it, listRau) }

        list_view.setOnItemClickListener { adapterView, view, i, l ->
            if (listRau.get(i).dotRau_id == 1) {
                var intent: Intent = Intent(this, ChiTietSanLuongRauActivity::class.java);
                startActivity(intent);
            }

        }


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

        // set swipe
        list_view.setMenuCreator(creator)
        list_view.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        var intent: Intent = Intent(applicationContext, SuaDotSanLuongActivity::class.java);
                        startActivity(intent);
                    }
                    1 -> {
                        Toast.makeText(
                            this@ChiTietDotSanLuongActivity,
                            listRau[position].toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })


//su kien them san luong
        themDotSanLuong.setOnClickListener {

            var intent: Intent = Intent(this, ThemDotSanLuongActivity::class.java);
            startActivity(intent);
        }
    }


    private fun generateDotRauData(): ArrayList<DotRau> {
        var result = ArrayList<DotRau>()
        var dr: DotRau = DotRau()
        dr.dotRau_id = 1
        dr.dotRau_name = "Đợt 1 "
        dr.dotRau_photo = R.drawable.kv2
        result.add(dr)

        dr = DotRau()
        dr.dotRau_id = 2
        dr.dotRau_name = "Đợt 2 "
        dr.dotRau_photo = R.drawable.kv2
        result.add(dr)

        dr = DotRau()
        dr.dotRau_id = 3
        dr.dotRau_name = "Đợt 3 "
        dr.dotRau_photo = R.drawable.kv2
        result.add(dr)

        dr = DotRau()
        dr.dotRau_id = 4
        dr.dotRau_name = "Đợt 4 "
        dr.dotRau_photo = R.drawable.kv2
        result.add(dr)

        return result
    }
}
