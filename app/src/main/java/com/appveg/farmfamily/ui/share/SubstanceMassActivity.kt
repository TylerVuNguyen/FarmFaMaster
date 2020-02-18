package com.appveg.farmfamily.ui.share

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.AddDeviceActivity
import com.appveg.farmfamily.ui.device.DeviceAdapter
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import kotlinx.android.synthetic.main.activity_substance_mass.*
import kotlinx.android.synthetic.main.fragment_device.*

class SubstanceMassActivity : AppCompatActivity() {
    var activity = this@SubstanceMassActivity

    private lateinit var database: Database

    var substances: ArrayList<Substance> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_substance_mass)
        substances = getListSubstance()
        list_view_substance.adapter = SubstanceMassAdapter(activity, substances)

        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.activity
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
        list_view_substance.setMenuCreator(creator)
        list_view_substance.setOnMenuItemClickListener { position, menu, index ->
            when (index) {
                0 -> {
                   // getForwardData(position)
                }
            }// open
            // delete
            // false : close the menu; true : not close the menu
            false
        }
        view_substance_btn_add.setOnClickListener {
            var gardenId = getDataFromItent()
            var intent: Intent = Intent(activity, AddSubstanceMassActivity::class.java)
            intent.putExtra("garden_id",gardenId)
            startActivity(intent)
        }
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("garden_id") as Int
    }


    /**
     * This method is to get list category
     */
    private fun getListSubstance(): ArrayList<Substance> {
        database = Database(activity)
        var gardenId = getDataFromItent()
        var substanceList = database.findAllSubstanceByGardenId(gardenId)
        if (substanceList.isNullOrEmpty()) {
            Toast.makeText(activity, getString(R.string.substance_empty_vi), Toast.LENGTH_LONG).show()
        }
        return substanceList
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        substances = getListSubstance()
        list_view_substance.adapter = SubstanceMassAdapter(activity, substances)
    }



}
