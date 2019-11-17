package com.appveg.farmfamily.ui.send


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.vegetable.VegetableTemp
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.*
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.pickDateBD
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.pickDateKT
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.positionSpinner
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.textViewPickKT
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.textViewPickStart
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ThemDotSanLuongActivity : AppCompatActivity() {
   private val activity = this@ThemDotSanLuongActivity

    private lateinit var database: Database

    private var selected: String? = ""
    private var listVeg: ArrayList<VegetableTemp> = ArrayList()
    private var sumQty : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_dot_san_luong)

        /*event add data to lisview*/
        addSoSL.setOnClickListener {
            // add item
            addVegTemp()
            // recal total quantity
            sumQuantity()
        }
        /*event insert data to database and validate*/
        addAllQty.setOnClickListener {
            verifyFromSQLite()
        }
        /*event remove data for temp listview*/
        lv_themSL.setOnItemClickListener { adapterView, view, i, l ->
            removeDataListVeg(i)
        }


        cancel_action.setOnClickListener {
            val intent: Intent = Intent(this, ChiTietDotSanLuongActivity::class.java)
            startActivity(intent)
        }

        // get create date
        pickDateBD.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    textViewPickStart.error = null
                    textViewPickStart.clearFocus()
                    textViewPickStart.setText("" + dayOfMonth + "/" + month + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }
        // get the end date
        pickDateKT.setOnClickListener {
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    textViewPickKT.error = null
                    textViewPickKT.clearFocus()
                    textViewPickKT.setText("" + dayOfMonth + "/" + month + "/" + year)
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
        positionSpinner.adapter = adapter

        positionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
    //ket thuc fun onCreate

    /**
     * the method to insert data batch detail
     */
    private fun addVegTemp(){
        val quantityVegetable = txt_qtyVeg.text.toString()
        var vegetableTemp: VegetableTemp = VegetableTemp()
        if(quantityVegetable.trim()!=""){
            vegetableTemp.vegName = selected.toString()
            vegetableTemp.vegQty = quantityVegetable.toInt()
        }else{
            var vegNumber = 0
            vegetableTemp.vegName = selected.toString()
            vegetableTemp.vegQty = vegNumber
        }
        listVeg.add(vegetableTemp)

        //hien thi list view
        lv_themSL.adapter = ThemAdapter(activity,listVeg)

    }
    /**
     * the method to sum total quantity
     */
    private fun sumQuantity(){
        sumQty = 0
        for(item in listVeg){
            var x: Int = item.vegQty!!.toInt()
            sumQty += x
        }
        totalQty.text = sumQty.toString().trim() + "/kg"
    }
    /**
     * This method is to validate the input text fields and verify add batch credentials from SQLite
     */
    private fun verifyFromSQLite(){
        database = Database(activity)
        var selectedStartDate = textViewPickStart.text.toString().trim()
        var selectedEndDate = textViewPickKT.text.toString().trim()
        var selectedBatchName = batchName.text.toString()
        var totalQty = sumQty.toString().trim()

        var checkStartDate = checkStartDate(selectedStartDate)
        var checkEndDate = checkEndDate(selectedEndDate)
        var checkBatchName = checkBatchName(selectedBatchName)

        var batch: Batch = Batch(null,"R.drawable.kv2",selectedBatchName,selectedEndDate,totalQty,1,"admin",selectedStartDate)
        if(checkStartDate && checkEndDate && checkBatchName){
            /*format date*/
            val current = Calendar.getInstance().time
            val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted: String = formatter.format(current)
            /*end format date*/
            try {
                var id : Long = database!!.addBatch(batch)
                if(!listVeg.isNullOrEmpty()){
                    for (item in listVeg){
                        var batchQtyDetail : BatchQtyDetail = BatchQtyDetail(null,id.toInt(),item.vegName,item.vegQty.toString(),"admin",formatted)
                        database!!.addBatchDetail(batchQtyDetail)
                    }
                }
                Toast.makeText(applicationContext,getString(R.string.batch_add_success),
                    Toast.LENGTH_LONG).show()
            }catch (e : Exception){
                Log.d("AAA",e.message)
                Toast.makeText(applicationContext,getString(R.string.batch_add_failed),
                    Toast.LENGTH_LONG).show()
            }
        }

    }
    /**
     * This method is to check start date
     */
    private fun checkStartDate(check: String) : Boolean {
        if (check.isEmpty()) {
            textViewPickStart.error = getString(R.string.error_start_date_batch)
            return false
        }
        return true
    }
    /**
     * This method is to check end date
     */
    private fun checkEndDate(check: String) : Boolean {
        if (check.isEmpty()) {
            textViewPickKT.error = getString(R.string.error_end_date_batch)
            return false
        }
        return true
    }
    /**
     * This method is to batch name
     */
    private fun checkBatchName(check: String) : Boolean {
        if (check.isEmpty()) {
            batchName.error = getString(R.string.error_name_batch)
            return false
        }
        return true
    }
    /**
     * This method is to remove data
     */
    private fun removeDataListVeg(id: Int){
        var adapter: ThemAdapter = ThemAdapter(this,listVeg)
        listVeg = adapter.removeItemPosition(id)
        //hien thi list view
        lv_themSL.adapter = ThemAdapter(activity,listVeg)
    }

}
