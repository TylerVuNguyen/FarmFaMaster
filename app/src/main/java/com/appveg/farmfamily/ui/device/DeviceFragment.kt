package com.appveg.farmfamily.ui.device

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.garden.Garden
import com.appveg.farmfamily.ui.garden.QLKVAdapter
import com.appveg.farmfamily.ui.garden.SuaKhuVuonActivity
import com.appveg.farmfamily.ui.send.SuaDotSanLuongActivity
import com.appveg.farmfamily.ui.vegetable.EditVegetableActivity
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_device_detail_status.*
import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.fragment_gallery.*

class DeviceFragment : Fragment() {
    private lateinit var database: Database

    var devices: ArrayList<Device> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_device, container, false)

        var listViewDevice = root.findViewById(R.id.list_view_device) as SwipeMenuListView

        val devices = getListDevice()

        listViewDevice.adapter = this.activity?.let { DeviceAdapter(it, devices) }


        listViewDevice.setOnItemClickListener { adapterView, view, i, l ->
                getForwardDataDetail(i)
        }

        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.context
            )
//
            // set item width
            editItem.width = 100

//            editItem.titleColor = Color.WHITE

            //set icon
            editItem.setIcon(R.drawable.ic_edit)
            // add to menu
            menu.addMenuItem(editItem)

            // create "delete" item
//            val deleteItem = SwipeMenuItem(
//                this.context
//            )

//            // set item width
//            deleteItem.width = 100
//            // set a icon
//            deleteItem.setIcon(R.drawable.ic_delete)
//            // add to menu
//            menu.addMenuItem(deleteItem)
        }

        // set swipe
        listViewDevice.setMenuCreator(creator)
        listViewDevice.setOnMenuItemClickListener { position, menu, index ->
            when (index) {
                0 -> {
                    getForwardData(position)
                }
            }// open
            // delete
            // false : close the menu; true : not close the menu
            false
        }

        var device_btn_add = root.findViewById(R.id.view_device_btn_add) as Button
        device_btn_add.setOnClickListener {
            var intent: Intent = Intent(requireContext(), AddDeviceActivity::class.java)
            startActivity(intent)
        }

        return root.rootView


    }
    /**
     * the method to display device
     */
    fun getListDevice() : ArrayList<Device>{
        database = Database(activity)
        devices = database.findAllDevice()
        if (devices.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách thiết bị đang trống !", Toast.LENGTH_LONG).show()
        }else{
            for (i in 0 until devices.size){
                var deviceDetails = database.findAllDeviceDetail(devices[i].deviceID!!.toInt())
                devices[i].deviceNum = "(" + deviceDetails.size.toString() + ")"
            }
        }
        return devices
    }

    /**
     * the method to itent data for Veg
     */
    fun getForwardData(position: Int){
        var device_id = devices[position].deviceID!!.toInt()
        var intent: Intent = Intent(activity, EditDeviceActivity::class.java)
        intent.putExtra("device_id",device_id)
        startActivity(intent)
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        devices = getListDevice()
        list_view_device.adapter = activity?.let { DeviceAdapter(it,devices) }
    }

    /**
     * the method to itent device detail
     */
    fun getForwardDataDetail(i: Int) {
        var device_id = devices[i].deviceID!!.toInt()
        var intent: Intent = Intent(activity, DeviceDetailActivity::class.java)
        intent.putExtra("device_id",device_id)
        startActivity(intent)
    }

}