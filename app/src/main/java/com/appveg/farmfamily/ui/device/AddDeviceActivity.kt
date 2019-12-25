package com.appveg.farmfamily.ui.device

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device_catogory.DeviceCategory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.activity_add_vegetable.*
import kotlinx.android.synthetic.main.activity_add_vegetable.selected_image
import kotlinx.android.synthetic.main.activity_them_dot_san_luong.positionSpinner
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import java.util.Random
import kotlin.collections.ArrayList

class AddDeviceActivity : AppCompatActivity() {
    var activity = this@AddDeviceActivity

    private var selected: String? = ""
    private var device_category_id: Long = -1

    private var REQUEST_CODE_CAMERA: Int = 123
    private var REQUEST_CODE_FOLDER: Int = 124

    private lateinit var database: Database

    var devices: ArrayList<Device> = ArrayList()

    private var deviceCategories: ArrayList<DeviceCategory> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)

        actionButton()
        actionButtonForImageView()


        //spinner hien thi danh sach rau
        val listSensor = getListCategory()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSensor)
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
                device_category_id = id

            }
        }
    }

    /**
     * This method to select image default
     */
    private fun actionButtonForImageView() {
        add_image_device_1.setOnClickListener {
            var device_img = R.drawable.phss
            var device_name_1 = "Cảm biến PH"
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_1)
            this.device_name.setSelection(device_name.text.length)
        }
        add_image_device_2.setOnClickListener {
            var device_img = R.drawable.tds
            var device_name_2 = "Cảm biến TDS"
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_2)
            this.device_name.setSelection(device_name.text.length)
        }
        add_image_device_3.setOnClickListener {
            var device_name_3 = "Cảm biến nhiệt độ"
            var device_img = R.drawable.cambiennhietdo
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_3)
            this.device_name.setSelection(device_name.text.length)
        }

        add_image_device_4.setOnClickListener {
            var device_name_4 = "Máy bơm"
            var device_img = R.drawable.maybo2
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_4)
            this.device_name.setSelection(device_name.text.length)
        }

        add_image_device_5.setOnClickListener {
            var device_name_5 = "Máy quạt"
            var device_img = R.drawable.quat
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_5)
            this.device_name.setSelection(device_name.text.length)
        }

        add_image_device_6.setOnClickListener {
            var device_name_6 = "Cảm biến mưa"
            var device_img = R.drawable.cambienmua
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_6)
            this.device_name.setSelection(device_name.text.length)
        }
        add_image_device_7.setOnClickListener {
            var device_name_7 = "Động cơ servo"
            var device_img = R.drawable.phunsuong2
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_7)
            this.device_name.setSelection(device_name.text.length)
        }
        add_image_device_8.setOnClickListener {
            var device_name_8 = "Cảm biến ánh sáng"
            var device_img = R.drawable.anhsangss
            this.selected_image_device.setImageResource(device_img)
            this.device_name.setText(device_name_8)
            this.device_name.setSelection(device_name.text.length)
        }
    }

    private fun actionButton() {
        /*event add veg*/
        btn_device_add.setOnClickListener {
            addDeviceAndDeviceDetail()
        }

        /*event call camera*/
        add_camera_device.setOnClickListener {
            getImageFromCamera()
        }
        /*event call image*/
        add_image_device.setOnClickListener {
            getImageFromGallery()
        }

    }

    private fun getImageFromCamera() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CODE_CAMERA
        )
    }

    private fun getImageFromGallery() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_FOLDER
        )
    }

    private fun addDeviceAndDeviceDetail() {
        database = Database(activity)
        var device_name = device_name.text.toString().trim()
        // var deviceCategoryName = selected.toString()
        var deviceCategoryId = device_category_id.toInt()

        var checkDeviceName = checkDeviceName(device_name)

        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        var bitmapDrawable: BitmapDrawable = selected_image_device.drawable as BitmapDrawable
        var bitmap: Bitmap = bitmapDrawable.bitmap
        var byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArray)


        var image: ByteArray = byteArray.toByteArray()
        var checkDeviceImage = checkDeviceImage(image)

        var codeDeviceDetail = getRandomCodeDetail()

        if (checkDeviceName && checkDeviceImage) {
            var device = Device(null, device_name, image, deviceCategoryId)
            var listDevice = getListDevice()
            var exits: Boolean = false
            var device_temp_id: Int = -1
            if (!listDevice.isNullOrEmpty()) {
                for (i in 0 until listDevice.size) {
                    if (listDevice[i].deviceName.equals(device_name)) {
                        exits = true
                        device_temp_id = listDevice[i].deviceID!!
                    }
                }
            }
            // have two case
            if (!exits) {
                var temp_id = database.addDeviceImageDefault(device)
                if (temp_id != null) {
                    var deviceDetail = DeviceDetail(
                        null,
                        codeDeviceDetail,
                        image,
                        temp_id.toInt(),
                        "N",
                        "admin",
                        formatted
                    )
                    database.addDeviceDetailImageDefault(deviceDetail)
                }
                Toast.makeText(this, getString(R.string.insert_data_success_vi), Toast.LENGTH_LONG)
                    .show()
                activity.finish()
            } else {
                var deviceDetail = DeviceDetail(
                    null,
                    codeDeviceDetail,
                    image,
                    device_temp_id,
                    "N",
                    "admin",
                    formatted
                )
                database.addDeviceDetailImageDefault(deviceDetail)

                Toast.makeText(this, getString(R.string.insert_data_success_vi), Toast.LENGTH_LONG)
                    .show()
                activity.finish()
            }

        } else {
            Toast.makeText(this, getString(R.string.insert_data_fail_vi), Toast.LENGTH_LONG).show()
        }

    }

    /**
     * This method is to batch name
     */
    private fun checkDeviceName(check: String): Boolean {
        database = Database(activity)
        var vegs = database.findAllVegetable()
        if (check.isEmpty()) {
            veg_name.error = getString(R.string.error_empty_common)
            return false
        } else {
            for (i in 0..vegs.size - 1) {
                if (check.equals(vegs.get(i).vegName, true)) {
                    veg_name.error = getString(R.string.error_veg_exist)
                    return false
                }
            }
        }
        return true
    }

    /**
     * This method is to batch name
     */
    private fun checkDeviceImage(check: ByteArray): Boolean {
        if (check.isEmpty()) {
            Toast.makeText(this, R.string.image_no_select_vi, Toast.LENGTH_LONG).show()
            return false
        }
        Log.d("CAT", check.size.toString())
        return true
    }

    /**
     * This method is to get permissions
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, REQUEST_CODE_CAMERA)
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
                return
            }

            else -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(intent, REQUEST_CODE_FOLDER)
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * This method is to set data for image view
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null) {
            val bitmap = data.extras!!.get("data") as Bitmap
            selected_image_device.setImageBitmap(bitmap)
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            try {
//                val inputStream = contentResolver.openInputStream(uri!!)
//                val bitmap = BitmapFactory.decodeStream(inputStream)
                Glide.with(this)
                    .load(uri)
                    .into(selected_image_device)

                //selected_image.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun getListDevice(): ArrayList<Device> {
        database = Database(activity)
        devices = database.findAllDevice()
        if (devices.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách thiết bị đang trống !", Toast.LENGTH_LONG).show()
        }
        return devices
    }

    fun getRandomCodeDetail(): String {
        var builder: StringBuilder = StringBuilder()
        builder.append("SS")
        val random = Random()
        var ran: Int = random.nextInt(9999 - 1000) + 1000
        builder.append(ran.toString())
        return builder.toString()
    }

    /**
     * This method is to get list category
     */
    private fun getListCategory(): ArrayList<String> {
        database = Database(activity)
        deviceCategories = database.findAllDeviceCategory()
        var categories: ArrayList<String> = ArrayList()
        if (!deviceCategories.isNullOrEmpty()) {
            for (i in 0 until deviceCategories.size) {
                categories.add(deviceCategories[i].dcategoryName!!)
            }
        }
        return categories
    }

}
