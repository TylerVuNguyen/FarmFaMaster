package com.appveg.farmfamily.ui.share

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetailAdapter
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import kotlinx.android.synthetic.main.activity_device_detail_status.*
import kotlinx.android.synthetic.main.activity_substance_mass.*
import kotlinx.android.synthetic.main.activity_substance_mass_detail.*

class SubstanceMassDetailActivity : AppCompatActivity() {

    private val activity = this@SubstanceMassDetailActivity

    private lateinit var database: Database

    var substanceDetails: ArrayList<SubstanceDetail> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_substance_mass_detail)

        substanceDetails = getListSubstanceDetail()
        list_view_substance_detail.adapter = SubstanceMassDetailAdapter(activity, substanceDetails)
        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.activity
            )
//            // set item background
//            openItem.background = ColorDrawable(
//                Color.rgb(0x00, 0x66,0xff
//                )
//            )
            // set item width
            editItem.width = 100
            // set item title
//            editItem.title = "Open"
//            // set item title fontsize
//            editItem.titleSize = 18
            // set item title font color
//            editItem.titleColor = Color.WHITE

            //set icon
            editItem.setIcon(R.drawable.ic_edit)
            // add to menu
            menu.addMenuItem(editItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.activity
            )
//            // set item background
//            deleteItem.background = ColorDrawable(
//                Color.rgb(
//                    0xF9,
//                    0x3F, 0x25
//                )
//            )
            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        list_view_substance_detail.setMenuCreator(creator)
        list_view_substance_detail.setOnMenuItemClickListener { position, menu, index ->
            when (index) {
                0 -> {
                    getForwardData(position)
                }
                1 -> {
                    // build alert dialog
                    val dialogBuilder = AlertDialog.Builder(activity)

                    // set message of alert dialog
                    dialogBuilder.setMessage(getString(R.string.delete_title_all_vi))
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id ->
                            removeSubstanceAndSubstanceDetail(position)
                        })
                        // negative button text and action
                        .setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })

                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle(getString(R.string.substance_delete_title_vi))
                    // show alert dialog
                    alert.show()
                }
            }// open
            // delete
            // false : close the menu; true : not close the menu
            false
        }
    }

    /**
     * This method is to get list category
     */
    private fun getListSubstanceDetail(): ArrayList<SubstanceDetail> {
        database = Database(activity)
        var gardenId = getDataFromItent()
        var substanceMassId = getSubstanceMassId()
        var substanceDetailList = database.findAllSubstanceDetailByGardenIdAndSubstance(gardenId,substanceMassId)
        if (substanceDetailList.isNullOrEmpty()) {
            Toast.makeText(activity, getString(R.string.substance_empty_vi), Toast.LENGTH_LONG).show()
        }
        return substanceDetailList
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("garden_id") as Int
    }

    /**
     * the method to get data from intent
     */
    private fun getSubstanceMassId(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("substance_mass_id") as Int
    }


    /**
     * the method to itent data for Veg
     */
    private fun getForwardData(position: Int){
        var substanceDetailId = substanceDetails[position].substanceMassDetailId!!.toInt()
        var intent: Intent = Intent(activity, EditSubstanceMassDetailActivity::class.java)
        intent.putExtra("substance_mass_detail_id",substanceDetailId)
        startActivity(intent)
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        substanceDetails = getListSubstanceDetail()
        list_view_substance_detail.adapter = SubstanceMassDetailAdapter(activity, substanceDetails)
    }

    /**
     * the method to removeBatch
     */
    private fun removeSubstanceAndSubstanceDetail(position: Int) {
        database = Database(activity)

        var substanceId: Int = getSubstanceMassId()
        var substance = database.findAllSubstanceBySubstanceId(substanceId)
        var substanceMassNum = substanceDetails[position].substanceMassDetailNum

        var result = database.deleteSubstanceDetail(substanceDetails[position].substanceMassDetailId!!.toInt())
        substanceDetails.remove(substanceDetails[position])
        if(substanceDetails.isNullOrEmpty()){
            database.deleteSubstance(substanceId)
            // finish because page no data
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
            activity.finish()
        }else if (result != null) {
            var tempTotal = substance.totalSubstanceMass.toDouble() - substanceMassNum.toDouble()
            var substance = Substance(substanceId , tempTotal.toString())
            database.updateSubstance(substance)
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
            list_view_substance_detail.adapter = SubstanceMassDetailAdapter(activity, substanceDetails)
        }
    }
}
