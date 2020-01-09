package com.appveg.farmfamily.ui.garden


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*

import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetail
import kotlinx.android.synthetic.main.activity_select_device_garden.*

class SelectDeviceGardenActivity : AppCompatActivity() {
    private val activity = this@SelectDeviceGardenActivity
    var deviceForGardens: ArrayList<DeviceDetail> = ArrayList()

    private lateinit var database: Database
    private lateinit var grid : GridView
    private lateinit var deviceCountSelect : TextView
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_device_garden)
        deviceCountSelect = activity.findViewById(R.id.count_select_device)
        grid = activity.findViewById(R.id.gird_device_for_garden_select)
        deviceForGardens = getListDeviceForGarden()
        var gardenId: Int = getDataFromItent()
        grid.adapter = this.activity?.let { SelectDeviceGardenAdapter (it, deviceForGardens,gardenId,count) }

        /*action button*/
        actionButton()
    }
    private fun actionButton() {
        /*event cancel*/
        btn_device_cancel.setOnClickListener {
            activity.finish()
        }

    }

    /**
     * the method to display device for garden
     */
    private fun getListDeviceForGarden() : ArrayList<DeviceDetail>{
        database = Database(activity)
        deviceForGardens = database.findAllDeviceDetailForGarden()
        if (deviceForGardens.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách thiết bị đang trống !", Toast.LENGTH_LONG).show()
        }else{
            for (item in 0 until deviceForGardens.size){
                if("Y" == deviceForGardens[item].deviceDetailStatus){
                    count++
                }
            }
            deviceCountSelect.text = "Đã thêm ($count) thiết bị"
        }
        return deviceForGardens
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        val id: Int =
            bundle.get("garden_id") as Int
        return id
    }

}
