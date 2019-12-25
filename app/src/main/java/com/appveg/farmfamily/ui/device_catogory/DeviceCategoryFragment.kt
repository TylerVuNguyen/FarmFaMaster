package com.appveg.farmfamily.ui.device_catogory

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.fragment_device_category.*

class DeviceCategoryFragment : Fragment() {

    private lateinit var database: Database

    private var deviceCategories: ArrayList<DeviceCategory> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_device_category, container, false)


        var listViewDeviceCategory = root.findViewById(R.id.list_view_device_category) as SwipeMenuListView

        deviceCategories = getListDeviceCategory()

        listViewDeviceCategory.adapter = this.activity?.let {DeviceCategoryAdapter(it, deviceCategories) }

//        swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.context
            )

            editItem.width = 100

//            set icon
            editItem.setIcon(R.drawable.ic_edit)
            // add to menu
            menu.addMenuItem(editItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.context
            )
//
            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        listViewDeviceCategory.setMenuCreator(creator)
        listViewDeviceCategory.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        getForwardData(position)
                        Toast.makeText(requireContext(),"ahihi", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        // build alert dialog
                        val dialogBuilder = AlertDialog.Builder(activity!!)

                        // set message of alert dialog
                        dialogBuilder.setMessage("Bạn có chắc chắn muốn xóa không ?")
                            // if the dialog is cancelable
                            .setCancelable(false)
                            // positive button text and action
                            .setPositiveButton("Có", DialogInterface.OnClickListener {
                                    dialog, id -> removeDeviceCategory(position)
                            })
                            // negative button text and action
                            .setNegativeButton("Hủy", DialogInterface.OnClickListener {
                                    dialog, id -> dialog.cancel()
                            })

                        // create dialog box
                        val alert = dialogBuilder.create()
                        // set title for alert dialog box
                        alert.setTitle("Xóa chi tiết rau")
                        // show alert dialog
                        alert.show()
                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })

        //button them rau
        var btn_add_device_category = root.findViewById(R.id.btn_add_device_category) as Button
        btn_add_device_category.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent: Intent = Intent(requireContext(), AddDeviceCategory::class.java)
                //listRoom_roomfunction.visibility = View.GONE
                startActivity(intent)
            }
        })

        return root.rootView

    }

    /**
     * the method to display batch
     */
    fun getListDeviceCategory() : ArrayList<DeviceCategory>{
        database = Database(activity)
        deviceCategories = database.findAllDeviceCategory()
        if (deviceCategories.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách rau đang trống !", Toast.LENGTH_LONG).show()
        }
        return deviceCategories
    }
    /**
     * the method to removeBatch
     */
    private fun removeDeviceCategory(position: Int) {
        database = Database(activity)
        var dcategoryId = database.deleteDeviceCategory(deviceCategories[position].dcategoryID!!.toInt())
        deviceCategories.remove(deviceCategories[position])
        if (dcategoryId != null) {
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
        }
        list_view_device_category.adapter = DeviceCategoryFragmentAdapter(activity, deviceCategories)
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        deviceCategories = getListDeviceCategory()
        list_view_device_category.adapter = activity?.let { DeviceCategoryFragmentAdapter(it,deviceCategories) }
    }

    /**
     * the method to itent data for Veg
     */
    fun getForwardData(position: Int){
        var dcategoryID = deviceCategories[position].dcategoryID!!.toInt()
        var intent: Intent = Intent(activity, EditDeviceCategory::class.java)
        intent.putExtra("device_category_id",dcategoryID)
        startActivity(intent)
    }
}