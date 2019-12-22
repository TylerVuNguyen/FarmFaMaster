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
import com.appveg.farmfamily.ui.send.SuaDotSanLuongActivity
import com.appveg.farmfamily.ui.vegetable.EditVegetableActivity
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.fragment_device.*

class DeviceFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_device, container, false)

        val listDevice = listDevice()

        var listViewDevice = root.findViewById(R.id.list_view_device) as ListView

        listViewDevice.adapter = this.activity?.let { DeviceAdapter(it, listDevice) }


        listViewDevice.setOnItemClickListener { adapterView, view, i, l ->
            if (listDevice[i].deviceID == 1) {
                var intent: Intent = Intent(requireContext(), DeviceDetailActivity::class.java)
                startActivity(intent)
            }
        }

        var device_btn_add = root.findViewById(R.id.view_device_btn_add) as Button
        device_btn_add.setOnClickListener {
            var intent: Intent = Intent(requireContext(), AddDeviceActivity::class.java)
            startActivity(intent)
        }

        return root.rootView


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