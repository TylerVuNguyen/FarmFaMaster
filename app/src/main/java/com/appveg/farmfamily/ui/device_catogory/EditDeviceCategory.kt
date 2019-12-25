package com.appveg.farmfamily.ui.device_catogory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import kotlinx.android.synthetic.main.activity_edit_device_category.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class EditDeviceCategory : AppCompatActivity() {

    private val activity = this@EditDeviceCategory
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_device_category)

        // function init data for edit
        initDataEdit()
        // action for button and image
        actionButton()
        // action for image default
        actionButtonForImageView()
    }

    /**
     * This method to select image default
     */
    private fun actionButtonForImageView() {
        device_category_image1_edit.setOnClickListener {
            var dcategory_img= R.drawable.cambienmua
            var dcatrgory_name1 = "Cảm biến "
            this.selected_device_category_image_edit.setImageResource(dcategory_img)
            this.device_category_name_edit.setText(dcatrgory_name1)
            this.device_category_name_edit.setSelection(device_category_name_edit.text.length)
        }
        device_category_image2_edit.setOnClickListener {
            var dcategory_img= R.drawable.quat
            var dcatrgory_name2 = "Quạt"
            this.selected_device_category_image_edit.setImageResource(dcategory_img)
            this.device_category_name_edit.setText(dcatrgory_name2)
            this.device_category_name_edit.setSelection(device_category_name_edit.text.length)
        }
        device_category_image3_edit.setOnClickListener {
            var dcategory_img = "Máy bơm"
            var dcatrgory_name3= R.drawable.maybo2
            this.selected_device_category_image_edit.setImageResource(dcatrgory_name3)
            this.device_category_name_edit.setText(dcategory_img)
            this.device_category_name_edit.setSelection(device_category_name_edit.text.length)
        }
    }

    private fun actionButton() {
        /*event add veg*/
        add_device_category_edit.setOnClickListener{
            editDeviceCategory()
        }

    }

    private fun editDeviceCategory() {
        database = Database(activity)
        var dcategory_name = device_category_name_edit.text.toString().trim()

        var checkDCategoryName = checkDCategoryName(dcategory_name)
        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        val dcategory_id = getDataFromItent()

        var bitmapDrawable: BitmapDrawable = selected_device_category_image_edit.drawable as BitmapDrawable
        var bitmap: Bitmap = bitmapDrawable.bitmap
        var byteArray: ByteArrayOutputStream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArray)

        var image: ByteArray = byteArray.toByteArray()
        var checkDCategoryImg = checkDCategoryImage(image)

        if(checkDCategoryName && checkDCategoryImg){
            var dcategory = DeviceCategory(dcategory_id, dcategory_name,image,formatted)
            database.updateDeviceCategoryImageDefault(dcategory)

            Toast.makeText(this,getString(R.string.update_data_success_vi),Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,getString(R.string.update_data_fail_vi), Toast.LENGTH_LONG).show()
        }

    }

    /**
     * This method is to batch name
     */
    private fun checkDCategoryName(check: String): Boolean {
        if (check.isEmpty()) {
            device_category_name_edit.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        val id: Int =
            bundle.get("device_category_id") as Int
        return id
    }

    /**
     * This method is to batch name
     */
    private fun checkDCategoryImage(check: ByteArray): Boolean {
        if (check.isEmpty()) {
            Toast.makeText(this,R.string.image_no_select_vi, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    /**
     * This method is to remove data
     */
    private fun initDataEdit() {
        val dcategory_id: Int = getDataFromItent()

        // gán lại id để tý update data
        var dcategory: DeviceCategory = getDCategoryById(dcategory_id)

        var imageBitmap : ByteArray? = dcategory.dcategoryImg
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)

        this.selected_device_category_image_edit.setImageBitmap(bitmap)
        this.device_category_name_edit.setText(dcategory.dcategoryName)
        this.device_category_name_edit.setSelection(device_category_name_edit.text.length)
    }

    /**
     * the method to get batch by id
     */
    fun getDCategoryById(dcategory_id: Int) : DeviceCategory{
        database = Database(activity)
        var dcategory : DeviceCategory = DeviceCategory()
        if(dcategory_id != null){
            dcategory = database.findDeviceCategoryId(dcategory_id)
        }
        return dcategory
    }

}