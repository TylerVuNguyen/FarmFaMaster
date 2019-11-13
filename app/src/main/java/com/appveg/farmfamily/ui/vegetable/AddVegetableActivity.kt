package com.appveg.farmfamily.ui.vegetable

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import java.io.*
import java.util.*

class AddVegetableActivity : AppCompatActivity() {

//    lateinit var editTextVeg: EditText
//    lateinit var imageView: ImageView
//    lateinit var imageView_1: ImageView
//    lateinit var imageView_2: ImageView
//    lateinit var imageView_3: ImageView
//
//    lateinit var btnBack: Button
//    lateinit var btnSave: Button
//    private var living: Boolean = true
//    private var bed: Boolean = false
//    private var kitchen: Boolean = false
//    private var loadImage: Boolean = false
//    private var REQUEST_CODE_CAMERA: Int = 123
//    private var REQUEST_CODE_FOLDER: Int = 124
//
//    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Thêm rau")
        setContentView(R.layout.activity_add_vegetable)

//        mapping()
//        actionButton()
//        actionRadioButtonForImageView()


    }
//
//    private fun mapping() {
//        editTextVeg = findViewById(R.id.addroom_roomName)
//        editTextVeg.setSelection(editTextVeg.text.length)
////        btnBack = findViewById(R.id.addveg_btn_back)
//        btnSave = findViewById(R.id.addveg_btn_save)
//        imageView = findViewById(R.id.addveg_addImageVeg)
//
//        imageView_1 = findViewById(R.id.addveg_img1)
//        imageView_2 = findViewById(R.id.addveg_img2)
//        imageView_3 = findViewById(R.id.addveg_img3)
//
//        progressBar = findViewById(R.id.addveg_progressbar)
//        progressBar.visibility = View.GONE
//
//        imageView_1.setBackgroundResource(R.drawable.border_img)
//        imageView_1.setImageResource(R.drawable.raucai)
//        living = true
//    }
//
//    private fun actionRadioButtonForImageView() {
//        imageView_3.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //xoa bo 3 cai layout cua 3 cai kia
//                imageView_3.setBackgroundResource(R.drawable.border_img)
//                imageView_2.setBackgroundResource(R.color.colorWhite)
//                imageView_1.setBackgroundResource(R.color.colorWhite)
//                imageView.setBackgroundResource(R.color.colorWhite)
//                living = false
//                bed = false
//                kitchen = true
//                loadImage = false
//            }
//        })
//
//        imageView_1.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //xoa bo 3 cai layout cua 3 cai kia
//                imageView_1.setBackgroundResource(R.drawable.border_img)
//                imageView_2.setBackgroundResource(R.color.colorWhite)
//                imageView_3.setBackgroundResource(R.color.colorWhite)
//                imageView.setBackgroundResource(R.color.colorWhite)
//                living = true
//                bed = false
//                kitchen = false
//                loadImage = false
//            }
//        })
//
//        imageView_2.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //xoa bo 3 cai layout cua 3 cai kia
//                imageView_2.setBackgroundResource(R.drawable.border_img)
//                imageView_1.setBackgroundResource(R.color.colorWhite)
//                imageView_3.setBackgroundResource(R.color.colorWhite)
//                imageView.setBackgroundResource(R.color.colorWhite)
//                living = true
//                bed = false
//                kitchen = false
//                loadImage = false
//            }
//        })
//    }
//
//    private fun actionButton() {
//        btnBack.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                startActivity(Intent(this@AddVegetableActivity, VegetableFragment::class.java))
//                this@AddVegetableActivity.finish()
//
//            }
//
//        })
//
//        btnSave.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                var vegName: String = editTextVeg.text.toString().trim()
//                if (vegName.trim() == "Phòng") {
//                    editTextVeg.setError("Vui lòng điền trường này!")
//                } else {
//                    //** thoa het tat ca roi
//                    Log.d("addroom", "Hoan thành hết các điều kiện rồi")
//                    SaveRoom().execute(vegName)
//                }
//            }
//
//        })
//
//
//        imageView.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                // setup the alert builder
//                val builder = AlertDialog.Builder(this@AddVegetableActivity)
//                builder.setTitle("Chọn hành động")
//                // add a list
//                val animals = arrayOf("Camera", "Chọn từ thư viện ảnh", "Thoát")
//                builder.setItems(animals) { dialog, which ->
//                    when (which) {
//                        0 -> {
//                            loadImage = true
//                            living = false
//                            kitchen = false
//                            bed = false
//                            imageView_2.setBackgroundResource(R.color.colorWhite)
//                            imageView_3.setBackgroundResource(R.color.colorWhite)
//                            imageView_1.setBackgroundResource(R.color.colorWhite)
//                            imageView.setBackgroundResource(R.drawable.border_img)
//                            getImageFromCamera()
//                        }
//                        1 -> {
//                            //check runtime permission
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
//                                    PackageManager.PERMISSION_DENIED
//                                ) {
//                                    //permission denied
//                                    val permissions =
//                                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                                    //show popup to request runtime permission
//                                    requestPermissions(permissions, 127)
//                                } else {
//                                    //permission already granted
//                                    imageView_2.setBackgroundResource(R.color.colorWhite)
//                                    imageView_3.setBackgroundResource(R.color.colorWhite)
//                                    imageView_1.setBackgroundResource(R.color.colorWhite)
//                                    imageView.setBackgroundResource(R.drawable.border_img)
//                                    loadImage = true
//                                    living = false
//                                    kitchen = false
//                                    bed = false
//                                    getImageFromGallery()
//                                }
//                            } else {
//                                imageView_2.setBackgroundResource(R.color.colorWhite)
//                                imageView_3.setBackgroundResource(R.color.colorWhite)
//                                imageView_1.setBackgroundResource(R.color.colorWhite)
//                                imageView.setBackgroundResource(R.drawable.border_img)
//                                loadImage = true
//                                living = false
//                                kitchen = false
//                                bed = false
//                                getImageFromGallery()
//                            }
//                        }
//                        2 -> {
//                            dialog.dismiss()
//                        }
//                    }
//                }
//
//                // create and show the alert dialog
//                val dialog = builder.create()
//                dialog.show()
//            }
//        })
//
//    }
//
//    private fun getImageFromCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, REQUEST_CODE_CAMERA)
//    }
//
//    private fun getImageFromGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"// tro toi noi chi lay hinh anh thoi: Hinh anh moi hien thi len
//        startActivityForResult(intent, REQUEST_CODE_FOLDER)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null) {
//            val bitmap = data.extras!!.get("data") as Bitmap
//            //luu xuong SDCard luon => tra ve cai uri
//
//            imageView.setImageBitmap(bitmap)
//        }
//        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
//            val uri = data.data
//            try {
//                val inputStream = contentResolver.openInputStream(uri!!)
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                //luu vao SDcard luon tra ve cai uri=> lan 2 cos the doi tiep
//
//                imageView.setImageBitmap(bitmap)
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
//
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//
//        when (requestCode) {
//            REQUEST_CODE_FOLDER -> {
//                // If request is cancelled, the result arrays are empty.
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    Toast.makeText(this@AddVegetableActivity, "Duoc phep", Toast.LENGTH_LONG).show()
//                    Log.d("logpermission", "Permission: Được phép")
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    Log.d("logpermission", "Permission: Không Được phép")
//                }
//                return
//            }
//
//            // Add other 'when' lines to check for other
//            // permissions this app might request.
//            else -> {
//                // Ignore all other requests.
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    inner class SaveRoom : AsyncTask<String, Void, String>() {
//        private var bitmap: BitmapDrawable? = null
//        override fun onPreExecute() {
//            super.onPreExecute()
//            progressBar.visibility = View.VISIBLE
//            if (living) {
//                bitmap = imageView_1.drawable as BitmapDrawable
//            }
//            if (bed) {
//                bitmap = imageView_2.drawable as BitmapDrawable
//            }
//            if (kitchen) {
//                bitmap = imageView_3.drawable as BitmapDrawable
//            }
//            if (loadImage) {
//                bitmap = imageView.drawable as BitmapDrawable
//            }
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//            //thuc hien them vao DB
//            var bitmapHA = bitmap!!.bitmap
//            //da co duoc bitmap
//            var uri = saveImageToExternalStorage(bitmapHA)
//            val database = Database(this@AddVegetableActivity)
//            if (database.addVeg(Vegetable(params[0]!!, uri.toString()))) {
//                return "${params[0]!!}"
//            } else {
//                return "null"
//            }
//        }
//
//        override fun onPostExecute(result: String?) {
//            if (result!! != "null") {
//                Log.d("addroom", "Lưu phòng thành công!")
//                //chuyen ve fragment quản lí phòng
//                progressBar.visibility = View.GONE
//                val intent = Intent(
//                    this@AddVegetableActivity,
//                    VegetableFragment::class.java
//                )
//                intent.putExtra("addroom_success", "success")
//                intent.putExtra("addroom_name", "$result")
//                startActivity(intent)
//                this@AddVegetableActivity.finish()
//            } else {
//                Log.d("addroom", "Lỗi save phòng")
//            }
//            super.onPostExecute(result)
//        }
//    }
//
//    // Method to save an image to external storage
//    private fun saveImageToExternalStorage(bitmap: Bitmap): Uri {
//        // Get the external storage directory path
//        // Create a file to save the image
//        var file = File(filesDir, "Images")
//        if (!file.exists()) {
//            file.mkdir()
//        }
//        file = File(file, "${UUID.randomUUID()}.png")
//        try {
//            // Get the file output stream
//            val stream: OutputStream = FileOutputStream(file)
//            // Compress the bitmap
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            // Flush the output stream
//            stream.flush()
//            // Close the output stream
//            stream.close()
//            Log.d("saveimagetest", "Image saved successful.")
//        } catch (e: IOException) { // Catch the exception
//            e.printStackTrace()
//            Log.d("saveimagetest", "Image NO NO successful.")
//        }
//        // Return the saved image path to uri
//        return Uri.parse(file.absolutePath)
//    }
//





}
