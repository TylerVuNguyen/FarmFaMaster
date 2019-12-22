package com.appveg.farmfamily.ui.device

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_device_detail_status.*

class DeviceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail_status)

            val listDevice = listDevice()

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

                //set icon
                editItem.setIcon(R.drawable.ic_edit)
                // add to menu
                menu.addMenuItem(editItem)

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
        list_view_device_detail.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
                override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                    when (index) {
                        0 -> {
                            var intent: Intent = Intent(applicationContext, EditDeviceActivity::class.java);
                            startActivity(intent)
//                        Toast.makeText(requireContext(), listDevice[position].toString(), Toast.LENGTH_LONG).show()
                        }
                        1 -> {
                            Toast.makeText(applicationContext, listDevice[position].toString(), Toast.LENGTH_LONG).show()
                        }
                    }// open
                    // delete
                    // false : close the menu; true : not close the menu
                    return false
                }
            })

            //button them rau
            viewdevice_btn_add.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    var intent: Intent = Intent(applicationContext, AddDeviceActivity::class.java)
                    startActivity(intent)
                }


            })

        }

        private fun listDevice(): ArrayList<Device> {
            var result = ArrayList<Device>()
            var device: Device = Device()
            device.deviceID = 1
            device.deviceName = "Máy bơm "
            device.deviceImg = R.drawable.maybo2
            result.add(device)

            device = Device()
            device.deviceID = 2
            device.deviceName = "Máy đo nhiệt độ "
            device.deviceImg = R.drawable.cambiennhietdo
            result.add(device)

            device = Device()
            device.deviceID = 3
            device.deviceName = "Máy đo dinh dưỡng"
            device.deviceImg = R.drawable.dinhduong
            result.add(device)

            device = Device()
            device.deviceID = 4
            device.deviceName = "Máy đo pH"
            device.deviceImg = R.drawable.doph
            result.add(device)

            return result
        }
    }

