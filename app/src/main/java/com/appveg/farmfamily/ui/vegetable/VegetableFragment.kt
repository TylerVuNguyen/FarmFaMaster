package com.appveg.farmfamily.ui.vegetable

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
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.fragment_vegetable.*

class VegetableFragment  : Fragment() {

    private lateinit var database: Database

    var vegetables: ArrayList<Vegetable> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_vegetable, container, false)

        var listViewVegetable = root.findViewById(R.id.list_view_vegetable) as SwipeMenuListView

        vegetables = getListVeg()

        listViewVegetable.adapter = this.activity?.let { VegetableFragmentAdapter(it, vegetables) }

        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
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
                    getForwardData(position)
                }
                1 -> {
                    // build alert dialog
                    val dialogBuilder = AlertDialog.Builder(activity)

                    // set message of alert dialog
                    dialogBuilder.setMessage("Bạn có chắc chắn muốn xóa không ?")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id -> removeVegetable(position)
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

        //button them rau
        var viewveg_btn_add = root.findViewById(R.id.viewveg_btn_add) as Button
        viewveg_btn_add.setOnClickListener {
            var intent: Intent = Intent(requireContext(), AddVegetableActivity::class.java)
            //activity?.onBackPressed()
            //listRoom_roomfunction.visibility = View.GONE
            startActivity(intent)
        }

        return root.rootView


    }

    /**
     * the method to display batch
     */
    private fun getListVeg() : ArrayList<Vegetable>{
        database = Database(activity)
        vegetables = database.findAllVegetable()
        if (vegetables.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách rau đang trống !", Toast.LENGTH_LONG).show()
        }
        return vegetables
    }
    /**
     * the method to removeBatch
     */
    private fun removeVegetable(position: Int) {
        database = Database(activity)
        var vegId = database.deleteVeg(vegetables[position].vegID!!.toInt())
        // delete param if exits
        if(vegetables[position].paramId != 0){
             database.deleteParam(vegetables[position].paramId)
        }
        vegetables.remove(vegetables[position])
        if (vegId != null) {
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
        }
        list_view_vegetable.adapter = this.activity?.let { VegetableFragmentAdapter(it, vegetables) }
    }

    /**
     * the method to itent data for Veg
     */
    private fun getForwardData(position: Int){
        var vegId = vegetables[position].vegID!!.toInt()
        var intent: Intent = Intent(activity, EditVegetableActivity::class.java)
        intent.putExtra("veg_id",vegId)
        startActivity(intent)
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        vegetables = getListVeg()
        list_view_vegetable.adapter = activity?.let { VegetableFragmentAdapter(it, vegetables) }
    }

}