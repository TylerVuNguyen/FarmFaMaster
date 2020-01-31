package com.appveg.farmfamily.ui.send

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.vegetable.VegetableTemp
import kotlinx.android.synthetic.main.activity_chi_tiet_tung_san_luong.*
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.*

class ChiTietSanLuongRauActivity : AppCompatActivity() {

    private lateinit var database: Database

    private var selected: String? = ""
    private var listVeg: ArrayList<VegetableTemp> = ArrayList()

    private val activity = this@ChiTietSanLuongRauActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_tung_san_luong)

        /*init data edit*/
        initBatchEdit()

        /*event cancel*/
//        cancel_action_detail.setOnClickListener {
//            var intent: Intent = Intent(activity, ChiTietDotSanLuongActivity::class.java)
//            intent.putExtra("garden_id", getDataFromItent())
//            activity.finish()
//            startActivity(intent)
//        }

        /*event back*/
//        backQty_Detail.setOnClickListener {
//            var intent: Intent = Intent(activity, ChiTietDotSanLuongActivity::class.java)
//            intent.putExtra("garden_id", getDataFromItent())
//            activity.finish()
//            startActivity(intent)
//        }

    }

    /**
     * the method to get batch by id
     */
    fun getBatchById(batch_id: Int) : Batch{
        database = Database(activity)
        var batch : Batch = Batch()
        if(batch_id != null){
            batch = database.findBatchById(batch_id)
        }
        return batch
    }
    /**
     * the method to get batch detail by id
     */
    fun getListBatchDetailById(batch_id: Int) : ArrayList<BatchQtyDetail>{
        var listBatchDetail: ArrayList<BatchQtyDetail> = ArrayList()
        database = Database(activity)
        if(batch_id != null){
            listBatchDetail = database.findAllBatchDetailById(batch_id)
        }
        return listBatchDetail
    }

    /**
     * This method is to remove data
     */
    private fun initBatchEdit() {
        val batch_id: Int = getDataFromItentBatchId()
        // gán lại id để tý update data
        var listBatchDetail: ArrayList<BatchQtyDetail> = getListBatchDetailById(batch_id)
        var batch: Batch = getBatchById(batch_id)

        txt_ngayBD.setText(batch.createdDate)
        txt_ngayKT.setText(batch.theEndDate)
        batchName_detail.setText(batch.batchName)
        totalQty_detail.text = batch.totalQuantity + "/kg"

        if(!listBatchDetail.isNullOrEmpty()){
            for (item in listBatchDetail) {
                var vegetable = VegetableTemp(item.qtyDetailId, item.vegetableName,item.vegetableQuantity?.toDouble())
                listVeg.add(vegetable)
            }
            if(listVeg.isNullOrEmpty()){
                Toast.makeText(activity,"Dánh sách chi tiết sản lượng đang trống !", Toast.LENGTH_LONG).show()
            }else{
                listview_Detail.adapter = ThemAdapter(activity, listVeg)
            }

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
     * the method to get data from intent batch id
     */
    private fun getDataFromItentBatchId(): Int {
        val bundle: Bundle = intent.extras
        val id: Int =
            bundle.get("batch_id") as Int
        return id
    }

}
