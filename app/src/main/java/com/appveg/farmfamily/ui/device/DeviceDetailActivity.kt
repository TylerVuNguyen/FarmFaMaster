package com.appveg.farmfamily.ui.device

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.send.ChiTietAdapter
import com.appveg.farmfamily.ui.send.ChiTietSanLuongRauActivity
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*
import kotlinx.android.synthetic.main.activity_device_detail_status.*
import kotlinx.android.synthetic.main.layout_device_detail.*

class DeviceDetailActivity : AppCompatActivity() {

    private val activity = this@DeviceDetailActivity

    private lateinit var database: Database

    var deviceDetails: ArrayList<DeviceDetail> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail_status)

        val listDevice = getListDeviceDetail()

        list_view_device_detail.adapter = DeviceDetailAdapter(activity, listDevice)
        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.applicationContext
            )
//
            // set item width
            editItem.width = 100

//            editItem.titleColor = Color.WHITE

//                //set icon
//                editItem.setIcon(R.drawable.ic_edit)
//                // add to menu
//                menu.addMenuItem(editItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.applicationContext
            )

            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        list_view_device_detail.setMenuCreator(creator)
        list_view_device_detail.setOnMenuItemClickListener { position, menu, index ->
            when (index) {
                0 -> {
                    Toast.makeText(
                        applicationContext,
                        listDevice[position].toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }// open
            // delete
            // false : close the menu; true : not close the menu
            false
        }

        //button them rau
        device_btn_update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent: Intent = Intent(applicationContext, AddDeviceActivity::class.java)
                startActivity(intent)
            }


        })

    }

    /**
     * the method to get data device
     */
    fun getListDeviceDetail() : ArrayList<DeviceDetail>{
        database = Database(activity)
        var device_id: Int = getDataFromItent()
        deviceDetails = database.findAllDeviceDetail(device_id)
        if (deviceDetails.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách thiết bị đang trống !", Toast.LENGTH_LONG).show()
        }else{
            for (i in 0 until deviceDetails.size){
                // convert status
                deviceDetails[i].deviceDetailStatus = convertStatus(deviceDetails[i].deviceDetailStatus!!)

            }
        }
        return deviceDetails
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        val id: Int =
            bundle.get("device_id") as Int
        return id
    }
    /**
     * the method to convert status
     */
    private fun convertStatus(status: String): String{
        var result: String = ""
        if("N" == status){
            result = "Chưa sử dụng"
        }else if(("Y") == status){
            result = "Đang sử dụng"
        }else if(("B") == status){
            result = "Đã hư hỏng"
        }

        return result
    }
}

