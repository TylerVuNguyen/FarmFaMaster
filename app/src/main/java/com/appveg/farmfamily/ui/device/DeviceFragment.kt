package com.appveg.farmfamily.ui.device

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.send.SuaDotSanLuongActivity
import com.appveg.farmfamily.ui.vegetable.EditVegetableActivity
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView

class DeviceFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_device, container, false)

        val listDevice = listDevice()

        var listviewDevice = root.findViewById(R.id.list_view_device) as SwipeMenuListView


        listviewDevice.adapter = this.activity?.let { DeviceAdapter(it, listDevice) }


        listviewDevice.setOnItemClickListener { adapterView, view, i, l ->
            //            if (listVeg.get(i).vegID == 1) {
//                var intent: Intent = Intent(requireContext(), EditDeviceActivity::class.java);
//                startActivity(intent)
//            }
            Toast.makeText(requireContext(),"ahihi", Toast.LENGTH_SHORT).show()

        }


        //swipemenulistview
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
            val deleteItem = SwipeMenuItem(
                this.context
            )

            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        listviewDevice.setMenuCreator(creator)
        listviewDevice.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        var intent: Intent = Intent(requireContext(), EditDeviceActivity::class.java);
                        startActivity(intent)
//                        Toast.makeText(requireContext(), listDevice[position].toString(), Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        Toast.makeText(requireContext(), listDevice[position].toString(), Toast.LENGTH_LONG).show()
                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })

        //button them rau
        var viewdevice_btn_add = root.findViewById(R.id.viewdevice_btn_add) as Button
        viewdevice_btn_add.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var intent: Intent = Intent(requireContext(), AddDeviceActivity::class.java)
                startActivity(intent)
            }


        })

        return root.rootView


    }

    private fun listDevice(): ArrayList<Device> {
        var result = ArrayList<Device>()
        var device: Device = Device()
        device.deviceID = 1
        device.deviceName = "Máy bơm "
        device.deviceImg = R.drawable.maybom
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