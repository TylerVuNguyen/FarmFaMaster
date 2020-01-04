package com.appveg.farmfamily.ui.garden

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    private lateinit var database: Database

    var gardens: ArrayList<Garden> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        var listViewGarden = root.findViewById(R.id.list_view_garden) as SwipeMenuListView

        gardens = getListGarden()

        listViewGarden.adapter = this.activity?.let { QLKVAdapter(it, gardens) }

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
        listViewGarden.setMenuCreator(creator)
        listViewGarden.setOnMenuItemClickListener { position, menu, index ->
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
                        .setPositiveButton("Có", DialogInterface.OnClickListener { dialog, id -> removeGarden(position)
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

        //button them khu vườn
        var btn_garden_add = root.findViewById(R.id.garden_btn_add) as Button
        btn_garden_add.setOnClickListener {
            var intent: Intent = Intent(requireContext(), ThemKhuVuonActivity::class.java)
            startActivity(intent)
        }

        listViewGarden.setOnItemClickListener { adapterView, view, i, l ->
            var intent: Intent = Intent(requireContext(), AddDeviceForGardenActivity::class.java)
            startActivity(intent)
        }

        return root.rootView

    }

    /**
     * the method to display batch
     */
    fun getListGarden() : ArrayList<Garden>{
        database = Database(activity)
        gardens = database.findAllGarden()
        if (gardens.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách khu vườn đang trống !", Toast.LENGTH_LONG).show()
        }
        return gardens
    }
    /**
     * the method to removeBatch
     */
    private fun removeGarden(position: Int) {
        database = Database(activity)
        var garden_id = database.deleteGarden(gardens[position].gardenId!!.toInt())
        gardens.remove(gardens[position])
        if (garden_id != null) {
            Toast.makeText(
                activity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
        }
        list_view_garden.adapter = activity?.let { QLKVAdapter(it, gardens) }
    }

    /**
     * the method to itent data for Veg
     */
    fun getForwardData(position: Int){
        var garden_id = gardens[position].gardenId!!.toInt()
        var intent: Intent = Intent(activity, SuaKhuVuonActivity::class.java)
        intent.putExtra("garden_id",garden_id)
        startActivity(intent)
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        gardens = getListGarden()
        list_view_garden.adapter = activity?.let { QLKVAdapter(it,gardens) }
    }
}