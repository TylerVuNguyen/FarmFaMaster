package com.appveg.farmfamily.ui.send


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.vegetable.VegetableTemp
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.*
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SuaDotSanLuongActivity : AppCompatActivity() {
    private val activity = this@SuaDotSanLuongActivity

    private lateinit var database: Database

    private var selected: String? = ""
    private var listVeg: ArrayList<VegetableTemp> = ArrayList()
    private var sumQty : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_dot_san_luong)
        /*init data edit*/
        initBatchEdit()

        /*event add data to lisview*/
        addSoSL_edit.setOnClickListener {
            // add item
            addVegTemp()
            // recal total quantity
            sumQuantity()
        }

        /*event insert data to database and validate*/
        addQty_edit.setOnClickListener {
            verifyFromSQLite()
        }

        /*event remove data for temp listview*/
        lv_themSL_edit.setOnItemClickListener { adapterView, view, i, l ->
            removeDataListVeg(i)
        }

        /*event cancel*/
        cancel_action_edit.setOnClickListener {
            var intent: Intent = Intent(activity, ChiTietDotSanLuongActivity::class.java)
            intent.putExtra("garden_id", getDataFromItent())
            activity.finish()
            startActivity(intent)
        }

        /*event back*/
        backQty_edit.setOnClickListener {
            var intent: Intent = Intent(activity, ChiTietDotSanLuongActivity::class.java)
            intent.putExtra("garden_id", getDataFromItent())
            activity.finish()
            startActivity(intent)
        }

        // get create date
        pickDateBD_edit.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    textViewPickStart_edit.error = null
                    textViewPickStart_edit.clearFocus()
                    textViewPickStart_edit.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }
        // get the end date
        pickDateKT_edit.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    textViewPickKT_edit.error = null
                    textViewPickKT_edit.clearFocus()
                    textViewPickKT_edit.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        //spinner hien thi danh sach rau
        val listRau = arrayOf("Rau cải", "Rau ngót ", "Rau xà lách")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listRau)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        positionSpinner_edit.adapter = adapter

        positionSpinner_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // either one will work as well
                //val item = parent.getItemAtPosition(position) as String
                selected = adapter.getItem(position)
            }
        }

    }
    /**
     * the method to insert data batch detail
     */
    private fun addVegTemp() {
        val quantityVegetable = txt_qtyVeg_edit.text.toString()
        var vegetableTemp: VegetableTemp = VegetableTemp()
        var vegName = selected.toString()
        if (quantityVegetable.trim() != "") {
            if(listVeg.isNullOrEmpty()){
                vegetableTemp.vegName = selected.toString()
                vegetableTemp.vegQty = quantityVegetable.toInt()
                listVeg.add(vegetableTemp)
            }else {
                for (i in 0..listVeg.size - 1) {
                    if (vegName.equals(listVeg.get(i).vegName)) {
                        var x: Int = listVeg.get(i).vegQty!!.toInt()
                        x += quantityVegetable.toInt()
                        vegetableTemp.vegName = listVeg.get(i).vegName
                        vegetableTemp.vegQty = x
                        listVeg.remove(listVeg.get(i))
                        listVeg.add(vegetableTemp)
                    } else if (i == listVeg.size - 1) {
                        vegetableTemp.vegName = selected.toString()
                        vegetableTemp.vegQty = quantityVegetable.toInt()
                        listVeg.add(vegetableTemp)
                    }
                }
            }
        } else {
            var vegNumber = 0
            vegetableTemp.vegName = selected.toString()
            vegetableTemp.vegQty = vegNumber
            listVeg.add(vegetableTemp)
        }
        //display list view
        lv_themSL_edit.adapter = ThemAdapter(activity, listVeg)

    }

    /**
     * the method to sum total quantity
     */
    private fun sumQuantity() {
        sumQty = 0
        for (item in listVeg) {
            var x: Int = item.vegQty!!.toInt()
            sumQty += x
        }
        totalQty_edit.text = sumQty.toString().trim() + "/kg"
    }
    /**
     * This method is to validate the input text fields and verify add batch credentials from SQLite
     */
    private fun verifyFromSQLite() {
        database = Database(activity)
        var selectedStartDate = textViewPickStart_edit.text.toString().trim()
        var selectedEndDate = textViewPickKT_edit.text.toString().trim()
        var selectedBatchName = batchName_edit.text.toString()
        var totalQty = sumQty.toString().trim()

        /*id intent from batch*/
        var garden_id = getDataFromItent()
        var batch_id = getDataFromItentBatchId()

        var checkStartDate = checkStartDate(selectedStartDate)
        var checkEndDate = checkEndDate(selectedEndDate)
        var checkBatchName = checkBatchName(selectedBatchName)

        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        var batch: Batch = Batch(batch_id,selectedBatchName,selectedEndDate,totalQty,garden_id,selectedStartDate,"admin",formatted)
        if (checkStartDate && checkEndDate && checkBatchName) {

            /*end format date*/
            try {
                var id: Int = database!!.updateBatch(batch)
                if (!listVeg.isNullOrEmpty()) {
                    for (item in listVeg) {
                        // có hai trường hợp:
                        // 1 là đã tồn tại chỉ cần update
                        // 2 là thêm mới
                        if(item.vegId != null && item.vegId != -1){
                            var batchQtyDetail: BatchQtyDetail = BatchQtyDetail(
                                item.vegId,
                                batch_id,
                                item.vegName,
                                item.vegQty.toString(),
                                "admin",
                                "admin",
                                formatted
                            )
                            database!!.updateBatchDetail(batchQtyDetail)
                        }else{
                            var batchQtyDetail: BatchQtyDetail = BatchQtyDetail(
                                null,
                                batch_id,
                                item.vegName,
                                item.vegQty.toString(),
                                "admin",
                                formatted
                            )
                            database!!.addBatchDetail(batchQtyDetail)
                        }

                    }
                }
                if(id != null){
                    Toast.makeText(
                        applicationContext, getString(R.string.update_data_success_vi),
                        Toast.LENGTH_LONG
                    ).show()
                }
                // reset list data for batch
                var  intent: Intent  = Intent(activity, ChiTietDotSanLuongActivity::class.java)
                intent.putExtra("garden_id",garden_id)
                startActivity(intent)
            } catch (e: Exception) {
                Log.d("AAA", e.message)
                Toast.makeText(
                    applicationContext, getString(R.string.update_data_fail_vi),
                    Toast.LENGTH_LONG
                ).show()
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

    /**
     * This method is to check start date
     */
    private fun checkStartDate(check: String): Boolean {
        if (check.isEmpty()) {
            textViewPickStart_edit.error = getString(R.string.error_start_date_batch)
            return false
        }
        return true
    }

    /**
     * This method is to check end date
     */
    private fun checkEndDate(check: String): Boolean {
        if (check.isEmpty()) {
            textViewPickKT_edit.error = getString(R.string.error_end_date_batch)
            return false
        }
        return true
    }

    /**
     * This method is to batch name
     */
    private fun checkBatchName(check: String): Boolean {
        if (check.isEmpty()) {
            batchName_edit.error = getString(R.string.error_name_batch)
            return false
        }
        return true
    }

    /**
     * This method is to remove data
     */
    private fun removeDataListVeg(id: Int) {
        var adapter: ThemAdapter = ThemAdapter(this, listVeg)
        listVeg = adapter.removeItemPosition(id)
        //hien thi list view
        lv_themSL_edit.adapter = ThemAdapter(activity, listVeg)
        // remove item of list then must be update list
        sumQuantity()
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

        textViewPickStart_edit.setText(batch.createdDate)
        textViewPickKT_edit.setText(batch.theEndDate)
        batchName_edit.setText(batch.batchName)
        totalQty_edit.text = batch.totalQuantity + "/kg"

        if(!listBatchDetail.isNullOrEmpty()){
            for (item in listBatchDetail) {
                var vegetable = VegetableTemp(item.qtyDetailId, item.vegetableName,item.vegetableQuantity?.toInt())
                listVeg.add(vegetable)
            }
            if(listVeg.isNullOrEmpty()){
                Toast.makeText(activity,"Dánh sách rau đang trống !",Toast.LENGTH_LONG).show()
            }else{
                lv_themSL_edit.adapter = ThemAdapter(activity, listVeg)
            }

        }
    }
}