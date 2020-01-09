package com.appveg.farmfamily.ui.garden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.device.Device

import kotlinx.android.synthetic.main.activity_add_device_for_garden.*
import kotlinx.android.synthetic.main.fragment_gallery.*

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
        var gardenId = getDataFromItent()
        /*event add veg*/
        btn_add_veg_garden_forward.setOnClickListener {
            var intent: Intent = Intent(activity,SelectVegGardenActivity::class.java)
            intent.putExtra("garden_id",gardenId)
            startActivity(intent)
        }
        btn_add_device_garden_forward.setOnClickListener {
            var intent: Intent = Intent(activity,SelectDeviceGardenActivity::class.java)
            intent.putExtra("garden_id",gardenId)
            startActivity(intent)
        }

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

    /**
     * the method to resume ( call when back stack)
     */
//    override fun onResume() {
//        super.onResume()
//        gardens = getListGarden()
//        list_view_garden.adapter = activity?.let { QLKVAdapter(it,gardens) }
//    }
}
