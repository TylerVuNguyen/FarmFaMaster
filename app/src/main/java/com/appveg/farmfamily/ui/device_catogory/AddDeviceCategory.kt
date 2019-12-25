package com.appveg.farmfamily.ui.device_catogory

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import kotlinx.android.synthetic.main.activity_add_device_category.*
import kotlinx.android.synthetic.main.fragment_device_category.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddDeviceCategory : AppCompatActivity() {

    private val activity = this@AddDeviceCategory


    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device_category)


        actionButton()
        actionButtonForImageView()
    }

    private fun actionButton() {
        /*event add veg*/
        btn_add_category.setOnClickListener{
            addDeviceCategory()
        }


    }

    /**
     * This method to select image default
     */
    private fun actionButtonForImageView() {
        device_category_image1.setOnClickListener {
            var dcategory_img= R.drawable.cambienmua
            var dcatrgory_name1 = "Cảm biến "
            this.selected_category_image.setImageResource(dcategory_img)
            this.device_category_name.setText(dcatrgory_name1)
            this.device_category_name.setSelection(device_category_name.text.length)
        }
        device_category_image2.setOnClickListener {
            var dcategory_img= R.drawable.quat
            var dcatrgory_name2 = "Quạt"
            this.selected_category_image.setImageResource(dcategory_img)
            this.device_category_name.setText(dcatrgory_name2)
            this.device_category_name.setSelection(device_category_name.text.length)
        }
        device_category_image3.setOnClickListener {
            var dcategory_img = "Máy bơm"
            var dcatrgory_name3= R.drawable.maybo2
            this.selected_category_image.setImageResource(dcatrgory_name3)
            this.device_category_name.setText(dcategory_img)
            this.device_category_name.setSelection(device_category_name.text.length)
        }
    }

    /**
     * This method is to batch name
     */
    private fun checkDeviceCategoryName(check: String): Boolean {
        database = Database(activity)
        var deviceCategorys = database.findAllDeviceCategory()
        if (check.isEmpty()) {
            device_category_name.error = getString(R.string.error_empty_common)
            return false
        }else{
            for (i in 0..deviceCategorys.size - 1){
                if(check.equals(deviceCategorys.get(i).dcategoryName,true)){
                    device_category_name.error = getString(R.string.error_veg_exist)
                    return false
                }
            }
        }
        return true
    }

    /**
     * This method is to batch name
     */
    private fun checkDeviceCategoryImg(check: ByteArray): Boolean {
        if (check.isEmpty()) {
            Toast.makeText(this,R.string.image_no_select_vi, Toast.LENGTH_LONG).show()
            return false
        }
        Log.d("CAT",check.size.toString())
        return true
    }

    private fun addDeviceCategory() {
        database = Database(activity)
        var device_category_name = device_category_name.text.toString().trim()

        var checkVegName = checkDeviceCategoryName(device_category_name)
        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        var bitmapDrawable: BitmapDrawable = selected_category_image.drawable as BitmapDrawable
        var bitmap: Bitmap = bitmapDrawable.bitmap
        var byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArray)


        var image: ByteArray = byteArray.toByteArray()
        var checkDeviceCategoryImg = checkDeviceCategoryImg(image)

        var sizeImage = image.size
        if(sizeImage <= 2000000 ){
            if(checkVegName && checkDeviceCategoryImg ){
                var dcategory = DeviceCategory(null, device_category_name,image,"admin",formatted)
                var id  = database.addDeviceCategoryImageDefault(dcategory)
                if(id != null){
                    Toast.makeText(this,getString(R.string.insert_data_success_vi),Toast.LENGTH_LONG).show()
                    activity.finish()
                }
            }else{
                Toast.makeText(this,getString(R.string.insert_data_fail_vi),Toast.LENGTH_LONG).show()
            }
        }

    }
}
