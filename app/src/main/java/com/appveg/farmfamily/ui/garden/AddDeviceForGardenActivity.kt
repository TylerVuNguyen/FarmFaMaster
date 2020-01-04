package com.appveg.farmfamily.ui.garden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.device.Device
import com.appveg.farmfamily.ui.garden.AddDeviceForGardenAdapter
import kotlinx.android.synthetic.main.activity_add_device.*

import kotlinx.android.synthetic.main.activity_add_device_for_garden.*

class AddDeviceForGardenActivity : AppCompatActivity() {
    private val activity = this@AddDeviceForGardenActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device_for_garden)
        var adapter : AddDeviceForGardenAdapter? = null
        var lisrDeviceForKV : ArrayList<Device>
     //   lisrDeviceForKV = listDeviceForKV()
  //      adapter = AddDeviceForGardenAdapter(this, lisrDeviceForKV)

//        grid.adapter = adapter
//
//        grid.setOnItemClickListener { adapterView, view, i, l ->
//
//            Toast.makeText(
//                this,
//                " Selected Company is = " + lisrDeviceForKV.get(i).deviceName,
//                Toast.LENGTH_SHORT
//            ).show()
//
//        }

        /*action button*/
        actionButton()

    }

    private fun actionButton() {
        /*event add veg*/
        btn_add_veg_garden_forward.setOnClickListener {
            var intent: Intent = Intent(activity,SelectVegGardenActivity::class.java)
            startActivity(intent)
        }
        btn_add_device_garden_forward.setOnClickListener {
            var intent: Intent = Intent(activity,SelectDeviceGardenActivity::class.java)
            startActivity(intent)
        }

    }
//        private fun listDeviceForKV(): ArrayList<Device> {
//            var result = ArrayList<Device>()
//            var device: Device = Device()
//            device.deviceID = 1
//            device.deviceName = "Thiết bị 1"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//            device = Device()
//            device.deviceID = 2
//            device.deviceName = "Thiết bị 2"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//            device = Device()
//            device.deviceID = 3
//            device.deviceName = "Thiết bị 3"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//            device = Device()
//            device.deviceID = 4
//            device.deviceName = "Thiết bị 4"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//            device = Device()
//            device.deviceID = 4
//            device.deviceName = "Thiết bị 4"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//            device = Device()
//            device.deviceID = 4
//            device.deviceName = "Thiết bị 4"
//            device.deviceImg = R.drawable.kv2
//            result.add(device)
//
//
//
//
//            return result
//        }
}
