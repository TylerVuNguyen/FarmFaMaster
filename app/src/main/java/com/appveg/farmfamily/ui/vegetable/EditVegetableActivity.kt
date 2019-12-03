package com.appveg.farmfamily.ui.vegetable

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.send.Batch
import com.appveg.farmfamily.ui.send.BatchQtyDetail
import com.appveg.farmfamily.ui.send.ThemAdapter
import kotlinx.android.synthetic.main.activity_add_vegetable.*
import kotlinx.android.synthetic.main.activity_edit_vegetable.*
import kotlinx.android.synthetic.main.activity_sua_dot_san_luong.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*

class EditVegetableActivity : AppCompatActivity() {
    private val activity = this@EditVegetableActivity

    private var REQUEST_CODE_CAMERA: Int = 123
    private var REQUEST_CODE_FOLDER: Int = 124

    private var vegetables: ArrayList<Vegetable> = ArrayList()
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vegetable)
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
        add_image1_edit.setOnClickListener {
            var veg_img= R.drawable.xalach
            var veg_name_1 = "Xà Lách"
            this.selected_image_edit.setImageResource(veg_img)
            this.veg_name_edit.setText(veg_name_1)
            this.veg_name_edit.setSelection(veg_name_edit.text.length)
        }
        add_image2_edit.setOnClickListener {
            var veg_img= R.drawable.raucai
            var veg_name_2 = "Cải Thìa"
            this.selected_image_edit.setImageResource(veg_img)
            this.veg_name_edit.setText(veg_name_2)
            this.veg_name_edit.setSelection(veg_name_edit.text.length)
        }
        add_image3_edit.setOnClickListener {
            var veg_name_3 = "Xúp Lơ"
            var veg_img= R.drawable.xuplo
            this.selected_image_edit.setImageResource(veg_img)
            this.veg_name_edit.setText(veg_name_3)
            this.veg_name_edit.setSelection(veg_name_edit.text.length)
        }
    }

    private fun actionButton() {
        /*event add veg*/
        add_veg_edit.setOnClickListener{
            editVegetable()
        }

        /*event call camera*/
        add_camera_edit.setOnClickListener {
            getImageFromCamera()
        }
        /*event call image*/
        add_image_edit.setOnClickListener {
            getImageFromGallery()
        }

    }

    private fun getImageFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_FOLDER)
    }
    private fun editVegetable() {
        database = Database(activity)
        var veg_name = veg_name_edit.text.toString().trim()

        var checkVegName = checkVegName(veg_name)
        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        val veg_id = getDataFromItent()

        var bitmapDrawable: BitmapDrawable = selected_image_edit.drawable as BitmapDrawable
        var bitmap: Bitmap = bitmapDrawable.bitmap
        var byteArray: ByteArrayOutputStream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray)

        var image: ByteArray = byteArray.toByteArray()
        var checkVegImage = checkVegImage(image)

        if(checkVegName && checkVegImage){
            var vegetable = Vegetable(veg_id, veg_name,image,"admin",formatted)
            database.updateVegImageDefault(vegetable)
            Toast.makeText(this,getString(R.string.update_data_success_vi), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,getString(R.string.update_data_fail_vi), Toast.LENGTH_LONG).show()
        }

    }

    /**
     * This method is to batch name
     */
    private fun checkVegName(check: String): Boolean {
        if (check.isEmpty()) {
            veg_name_edit.error = getString(R.string.error_empty_common)
            return false
        }
        return true
    }
    /**
     * This method is to batch name
     */
    private fun checkVegImage(check: ByteArray): Boolean {
        if (check.isEmpty()) {
            Toast.makeText(this,R.string.veg_image_no_select_vi, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null) {
            val bitmap = data.extras!!.get("data") as Bitmap
            //luu xuong SDCard luon => tra ve cai uri

            photo_camera_edit.setImageBitmap(bitmap)
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                //luu vao SDcard luon tra ve cai uri=> lan 2 cos the doi tiep

                selected_image_edit.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        val id: Int =
            bundle.get("veg_id") as Int
        return id
    }

    /**
     * This method is to remove data
     */
    private fun initDataEdit() {
        val veg_id: Int = getDataFromItent()

        // gán lại id để tý update data
        var vegetable: Vegetable = getVegById(veg_id)

        var imageBitmap : ByteArray? = vegetable.vegImgBlob
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)

        this.selected_image_edit.setImageBitmap(bitmap)
        this.veg_name_edit.setText(vegetable.vegName)
        this.veg_name_edit.setSelection(veg_name_edit.text.length)
    }

    /**
     * the method to get batch by id
     */
    fun getVegById(veg_id: Int) : Vegetable{
        database = Database(activity)
        var vegetable : Vegetable = Vegetable()
        if(veg_id != null){
            vegetable = database.findVegetableById(veg_id)
        }
        return vegetable
    }
}
