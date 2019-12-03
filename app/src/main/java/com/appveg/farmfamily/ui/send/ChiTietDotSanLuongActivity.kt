package com.appveg.farmfamily.ui.send

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*
import android.widget.Toast
import com.appveg.farmfamily.ui.database.Database


class ChiTietDotSanLuongActivity : AppCompatActivity() {

    private val activity = this@ChiTietDotSanLuongActivity

    private lateinit var database: Database

    var bactchList: ArrayList<BatchCustom> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_san_luong)

        getListBatch()

        list_view.setOnItemClickListener { adapterView, view, i, l ->
            getForwardDataDetail(i)
        }

        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.applicationContext
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
                this.applicationContext
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
        list_view.setMenuCreator(creator)
        list_view.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
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
                            .setPositiveButton("Có", DialogInterface.OnClickListener {
                                    dialog, id -> removeBatch(position)
                            })
                            // negative button text and action
                            .setNegativeButton("Hủy", DialogInterface.OnClickListener {
                                    dialog, id -> dialog.cancel()
                            })

                        // create dialog box
                        val alert = dialogBuilder.create()
                        // set title for alert dialog box
                        alert.setTitle("Xóa đợt sản lượng")
                        // show alert dialog
                        alert.show()

                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })


    //su kien them san luong
    themDotSanLuong.setOnClickListener {
        var  intent: Intent  = Intent(activity, ThemDotSanLuongActivity::class.java)
        intent.putExtra("garden_id",getDataFromItent())
        activity.finish()
        startActivity(intent)
        }
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent() : Int {
        val bundle:Bundle = intent.extras
        val id: Int =
            bundle.get("garden_id") as Int
        return id

    }
    /**
     * the method to removeBatch
     */
    private fun removeBatch(position: Int) {
        database = Database(activity)
        var batch_id = database.deleteBatch(bactchList[position].batchId!!.toInt())
        bactchList.remove(bactchList[position])
        if (batch_id != null) {
            Toast.makeText(
                this@ChiTietDotSanLuongActivity,
                getString(R.string.deleted_data_success_vi),
                Toast.LENGTH_LONG
            ).show()
        }
        list_view.adapter = ChiTietAdapter(activity, bactchList)
    }
    /**
     * the method to display batch
     */
    fun getListBatch(){
        var id = getDataFromItent()
        database = Database(activity)
        bactchList = database.viewBatchByGardenId(id)
        if(bactchList.isNullOrEmpty()){
            Toast.makeText(activity,"Dánh sách đợt đang trống !",Toast.LENGTH_LONG).show()
        }else{
            list_view.adapter = ChiTietAdapter(activity, bactchList)
        }
    }


    /**
     * the method to itent data for batch and batch detail
     */
    fun getForwardData(position: Int){
        var gardenIdForward = getDataFromItent()
        var batch_id = bactchList[position].batchId!!.toInt()
        var intent: Intent = Intent(applicationContext, SuaDotSanLuongActivity::class.java)
        intent.putExtra("garden_id",gardenIdForward)
        intent.putExtra("batch_id",batch_id)
        activity.finish()
        startActivity(intent)
    }

    /**
     * the method to itent data for batch and batch detail
     */
    fun getForwardDataDetail(position: Int){
            var intent: Intent = Intent(activity, ChiTietSanLuongRauActivity::class.java)
            intent.putExtra("garden_id",getDataFromItent())
            intent.putExtra("batch_id",bactchList.get(position).batchId)
            activity.finish()
            startActivity(intent)
    }

}


