package com.appveg.farmfamily.ui.garden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetail
import com.appveg.farmfamily.ui.vegetable.Vegetable
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.activity_add_device_for_garden.*
import kotlinx.android.synthetic.main.activity_select_device_garden.*
import kotlinx.android.synthetic.main.activity_select_veg_garden.*

class SelectVegGardenActivity : AppCompatActivity() {
    private val activity = this@SelectVegGardenActivity
    var vegForGardens: ArrayList<Vegetable> = ArrayList()
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_veg_garden)

        var grid = activity.findViewById<GridView>(R.id.gird_veg_for_garden_select)
        vegForGardens = getListVegForGarden()

        grid.adapter = this.activity?.let { SelectVegGardenAdapter (it, vegForGardens) }

        /*action button*/
        actionButton()
    }
    private fun actionButton() {
        /*event cancel*/
        btn_veg_cancel.setOnClickListener {
            activity.finish()
        }

    }
    /**
     * the method to display batch
     */
    fun getListVegForGarden() : ArrayList<Vegetable>{
        database = Database(activity)
        vegForGardens = database.findAllVegetableForGarden()
        if (vegForGardens.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách thiết bị đang trống !", Toast.LENGTH_LONG).show()
        }
        return vegForGardens
    }
}
