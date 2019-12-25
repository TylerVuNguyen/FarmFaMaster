package com.appveg.farmfamily.ui.send


import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.vegetable.Vegetable
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
    private lateinit var sendFragment: SendFragment

    private var selected: String? = ""

    private var listVeg: ArrayList<VegetableTemp> = ArrayList()

    private var sumQty: Int = 0

    private var listVegetable: ArrayList<Vegetable> = ArrayList()

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
        addQty.setOnClickListener {
            verifyFromSQLite()
        }
        /*event remove data for temp listview*/
        lv_themSL.setOnItemClickListener { adapterView, view, i, l ->
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(activity)

            // set message of alert dialog
            dialogBuilder.setMessage("Bạn có chắc chắn muốn xóa không ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Có", DialogInterface.OnClickListener {
                        dialog, id -> removeDataListVeg(i)
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

        /*event cancel*/
        cancel_action.setOnClickListener {
            var  intent: Intent  = Intent(activity, ChiTietDotSanLuongActivity::class.java)
            intent.putExtra("garden_id",getDataFromItent())
            activity.finish()
            startActivity(intent)
        }

        /*event back*/
        backQty.setOnClickListener {
            var  intent: Intent  = Intent(activity, ChiTietDotSanLuongActivity::class.java)
            intent.putExtra("garden_id",getDataFromItent())
            activity.finish()
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
                    textViewPickStart.setText("" + dayOfMonth + "/" + (month + 1) + "/" + year)
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
                    textViewPickKT.setText("" + dayOfMonth + "/" + (month + 1) + "/" + year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        //spinner hien thi danh sach rau
        val listRau = getListVegetable()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listRau)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
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
    private fun addVegTemp() {
        val quantityVegetable = txt_qtyVeg.text.toString()
        var vegetableTemp: VegetableTemp = VegetableTemp()
        var vegName = selected.toString()
        if (quantityVegetable.isNotBlank()) {
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
            if(listVeg.isNullOrEmpty()){
                vegetableTemp.vegName = selected.toString()
                vegetableTemp.vegQty = vegNumber
                listVeg.add(vegetableTemp)
            }else {
                for (i in 0..listVeg.size - 1) {
                    if (vegName.equals(listVeg.get(i).vegName)) {
                        var x: Int = listVeg.get(i).vegQty!!.toInt()
                        x += vegNumber
                        vegetableTemp.vegName = listVeg.get(i).vegName
                        vegetableTemp.vegQty = x
                        listVeg.remove(listVeg.get(i))
                        listVeg.add(vegetableTemp)
                    } else if (i == listVeg.size - 1) {
                        vegetableTemp.vegName = selected.toString()
                        vegetableTemp.vegQty = vegNumber
                        listVeg.add(vegetableTemp)
                    }
                }
            }
        }
        //display list view
        lv_themSL.adapter = ThemAdapter(activity, listVeg)
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
        totalQty.text = sumQty.toString().trim() + "/kg"
    }

    /**
     * This method is to validate the input text fields and verify add batch credentials from SQLite
     */
    private fun verifyFromSQLite() {
        database = Database(activity)
        var selectedStartDate = textViewPickStart.text.toString().trim()
        var selectedEndDate = textViewPickKT.text.toString().trim()
        var selectedBatchName = batchName.text.toString()
        var totalQty = sumQty.toString().trim()

        var garden_id = getDataFromItent()

        var checkStartDate = checkStartDate(selectedStartDate)
        var checkEndDate = checkEndDate(selectedEndDate)
        var checkBatchName = checkBatchName(selectedBatchName)

        var batch: Batch = Batch(
            null,
            "R.drawable.kv2",
            selectedBatchName,
            selectedEndDate,
            totalQty,
            garden_id,
            "admin",
            selectedStartDate
        )
        if (checkStartDate && checkEndDate && checkBatchName) {
            /*format date*/
            val current = Calendar.getInstance().time
            val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted: String = formatter.format(current)

            /*end format date*/
            try {
                var id: Long = database!!.addBatch(batch)
                if (!listVeg.isNullOrEmpty()) {
                    for (item in listVeg) {
                        var batchQtyDetail: BatchQtyDetail = BatchQtyDetail(
                            null,
                            id.toInt(),
                            item.vegName,
                            item.vegQty.toString(),
                            "admin",
                            formatted
                        )
                        database!!.addBatchDetail(batchQtyDetail)
                    }
                }
                if(id != null){
                    Toast.makeText(
                        applicationContext, getString(R.string.insert_data_success_vi),
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
                    applicationContext, getString(R.string.insert_data_fail_vi),
                    Toast.LENGTH_LONG
                ).show()
            }
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
     * This method is to check start date
     */
    private fun checkStartDate(check: String): Boolean {
        if (check.isEmpty()) {
            textViewPickStart.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }

    /**
     * This method is to check end date
     */
    private fun checkEndDate(check: String): Boolean {
        if (check.isEmpty()) {
            textViewPickKT.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }

    /**
     * This method is to batch name
     */
    private fun checkBatchName(check: String): Boolean {
        if (check.isEmpty()) {
            batchName.error = getString(R.string.error_empty_common)
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
        lv_themSL.adapter = ThemAdapter(activity, listVeg)
        // remove item of list then must be update list
        sumQuantity()
    }

    /**
     * This method is to get list category
     */
    private fun getListVegetable(): ArrayList<String> {
        database = Database(activity)
        listVegetable = database.findAllVegetable()
        var categories: ArrayList<String> = ArrayList()
        if (!listVegetable.isNullOrEmpty()) {
            for (i in 0 until listVegetable.size) {
                categories.add(listVegetable[i].vegName!!)
            }
        }
        return categories
    }

}
