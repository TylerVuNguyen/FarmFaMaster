package com.appveg.farmfamily.ui.param


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceAdapter
import com.appveg.farmfamily.ui.vegetable.EditVegetableActivity
import com.appveg.farmfamily.ui.vegetable.Vegetable
import com.appveg.farmfamily.ui.vegetable.VegetableFragmentAdapter
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.fragment_device.*
import kotlinx.android.synthetic.main.fragment_thamso.*
import kotlinx.android.synthetic.main.fragment_vegetable.*

class ThamSoFragment : Fragment() {

    private lateinit var database: Database

    var vegetables: ArrayList<Vegetable> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_thamso, container, false)

        var listViewVegetable = root.findViewById(R.id.list_view_param) as SwipeMenuListView

        vegetables = getListVeg()

        listViewVegetable.adapter = this.activity?.let { ParamFragmentAdapter(it, vegetables) }

        listViewVegetable.setOnItemClickListener { adapterView, view, i, l ->
            checkedBeforeForwardData1(i)
        }

        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->

            val addItem = SwipeMenuItem(
                this.context
            )
//            // set item background
//            openItem.background = ColorDrawable(
//                Color.rgb(0x00, 0x66,0xff
//                )
//            )
            // set item width
            addItem.width = 100
            // set item title
//            editItem.title = "Open"
//            // set item title fontsize
//            editItem.titleSize = 18
            // set item title font color
//            editItem.titleColor = Color.WHITE

            //set icon
            addItem.setIcon(R.drawable.ic_add_param)
            // add to menu
            menu.addMenuItem(addItem)

            // create "open" item
            val editItem = SwipeMenuItem(
                this.context
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
                this.context
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
        listViewVegetable.setMenuCreator(creator)
        listViewVegetable.setOnMenuItemClickListener { position, menu, index ->
            when (index) {
                0 -> {
                    checkedBeforeForwardData(position)
                }
                1 -> {
                    getForwardData(position)
                }
                2 -> {
                    // build alert dialog
                    val dialogBuilder = AlertDialog.Builder(activity)

                    // set message of alert dialog
                    dialogBuilder.setMessage("Bạn có chắc chắn muốn xóa không ?")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id ->
                            removeParam(position)
                        })
                        // negative button text and action
                        .setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
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
            false
        }

        return root.rootView
    }


    /**
     * the method to display batch
     */
    private fun getListVeg() : ArrayList<Vegetable>{
        database = Database(activity)
        vegetables = database.findAllVegetableForParam()
        if (vegetables.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách rau đang trống !", Toast.LENGTH_LONG).show()
        }
        return vegetables
    }

    /**
     * the method to itent data for Veg
     */
    private fun checkedBeforeForwardData(position: Int){
        var vegId = vegetables[position].vegID!!.toInt()
        var vegetable : Vegetable = database.findVegetableById(vegId)
        if(vegetable.paramId != 0 ){
            Toast.makeText(activity,getString(R.string.message_setting_param),Toast.LENGTH_SHORT).show()
        }else{
            var intent: Intent = Intent(requireContext(), AddParamActivity::class.java)
            intent.putExtra("veg_id",vegId)
            startActivity(intent)
        }
    }

    /**
     * the method to itent data for Veg
     */
    private fun checkedBeforeForwardData1(position: Int){
        var vegId = vegetables[position].vegID!!.toInt()
        var vegetable : Vegetable = database.findVegetableById(vegId)
        if(vegetable.paramId != 0 ){
            var intent: Intent = Intent(requireContext(), ParamDetailActivity::class.java)
            intent.putExtra("param_id",vegetables[position].paramId)
            startActivity(intent)
        }else{
            Toast.makeText(activity,getString(R.string.message_no_setting_param),Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        vegetables = getListVeg()
        list_view_param.adapter = activity?.let { ParamFragmentAdapter(it,vegetables) }
    }

    /**
     * the method to itent data for param
     */
    private fun getForwardData(position: Int){
        var paramId = vegetables[position].paramId!!.toInt()
        if(paramId != 0 ){
            var intent: Intent = Intent(activity, EditParamActivity::class.java)
            intent.putExtra("param_id",paramId)
            startActivity(intent)
        }else{
            Toast.makeText(activity,getString(R.string.message_no_setting_param),Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * the method to removeBatch
     */
    private fun removeParam(position: Int) {
        database = Database(activity)
        var paramId = database.deleteParam(vegetables[position].paramId)
        if (paramId != null) {
            database.updateParamIdForVeg(0, vegetables[position].vegID!!)
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
        }
        vegetables = getListVeg()
        list_view_param.adapter = ParamFragmentAdapter(activity, vegetables)
    }
}