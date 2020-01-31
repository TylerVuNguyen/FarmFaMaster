package com.appveg.farmfamily.ui.garden


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.vegetable.VegetableFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.activity_add_vegetable.*
import kotlinx.android.synthetic.main.activity_sua_khu_vuon.*
import kotlinx.android.synthetic.main.activity_them_khu_vuon.*
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_1
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_2
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_3
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_4
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_5
import kotlinx.android.synthetic.main.activity_them_khu_vuon.add_image_garden_6
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class ThemKhuVuonActivity : AppCompatActivity() {
    private val activity = this@ThemKhuVuonActivity

    var gardens: ArrayList<Garden> = ArrayList()

    private var REQUEST_CODE_CAMERA: Int = 123
    private var REQUEST_CODE_FOLDER: Int = 124

    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_khu_vuon)
        // action for button and image
        actionButton()
        // action for image default
        actionButtonForImageView()

    }

    /**
     * This method to select image default
     */
    private fun actionButtonForImageView() {
        add_image_garden_1.setOnClickListener {
            var garden_img = R.drawable.kv2
            var garden_name_1 = "Khu vườn 1"
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_1)
            this.garden_name.setSelection(garden_name.text.length)
        }
        add_image_garden_2.setOnClickListener {
            var garden_img = R.drawable.kv2
            var garden_name_2 = "Khu vườn 2"
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_2)
            this.garden_name.setSelection(garden_name.text.length)
        }
        add_image_garden_3.setOnClickListener {
            var garden_name_3 = "Khu vườn 3"
            var garden_img = R.drawable.kv2
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_3)
            this.garden_name.setSelection(garden_name.text.length)
        }

        add_image_garden_4.setOnClickListener {
            var garden_img = R.drawable.kv2
            var garden_name_4 = "Khu vườn 1"
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_4)
            this.garden_name.setSelection(garden_name.text.length)
        }
        add_image_garden_5.setOnClickListener {
            var garden_img = R.drawable.kv2
            var garden_name_5 = "Khu vườn 2"
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_5)
            this.garden_name.setSelection(garden_name.text.length)
        }
        add_image_garden_6.setOnClickListener {
            var garden_name_6 = "Khu vườn 3"
            var garden_img = R.drawable.kv2
            this.selected_garden_image.setImageResource(garden_img)
            this.garden_name.setText(garden_name_6)
            this.garden_name.setSelection(garden_name.text.length)
        }
    }

    private fun actionButton() {
        /*event add garden*/
        btn_add_garden.setOnClickListener {
            addGarden()
        }

        /*event call camera*/
        add_garden_camera.setOnClickListener {
            getImageFromCamera()
        }
        /*event call image*/
        add_garden_image.setOnClickListener {
            getImageFromGallery()
        }

        /*event cancel*/
        cancel_action_garden.setOnClickListener {
            activity.finish()
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

    private fun addGarden() {
        database = Database(activity)
        var garden_name = garden_name.text.toString().trim()

        var garden_code = generateAssetTypeCode(garden_name)

        Toast.makeText(this, garden_code, Toast.LENGTH_LONG).show()

        var checkGardenName = checkGardenName(garden_name)
        /*format date*/
        val current = Calendar.getInstance().time
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted: String = formatter.format(current)

        var bitmapDrawable: BitmapDrawable = selected_garden_image.drawable as BitmapDrawable
        var bitmap: Bitmap = bitmapDrawable.bitmap
        var byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArray)


        var image: ByteArray = byteArray.toByteArray()
        var checkGardenImage = checkGardenImage(image)

        var sizeImage = image.size
        if (checkGardenName && checkGardenImage) {
            var garden = Garden(null, garden_code, garden_name, image, "admin", formatted)
            var id = database.addGardenImageDefault(garden)
            if (id != null) {
                Toast.makeText(this, getString(R.string.insert_data_success_vi), Toast.LENGTH_LONG)
                    .show()
                //var fragmentAdapter : GalleryFragment = GalleryFragment()
                // hide activity
                //addGarden_Function.visibility = View.GONE
                //action bar
                //supportFragmentManager.beginTransaction().add(R.id.fragmentContainer_garden, fragmentAdapter).addToBackStack(null).commit()
                //gardens = getListGarden()
                activity.finish()
            }

        } else {
            Toast.makeText(this, getString(R.string.insert_data_fail_vi), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * This method is to batch name
     */
    private fun checkGardenName(check: String): Boolean {
        database = Database(activity)
        var gardens = database.findAllGarden()
        var garden_temp = generateAssetTypeCode(check)
        if (check.isEmpty()) {
            garden_name.error = getString(R.string.error_empty_common)
            return false
        } else {
            for (i in 0 until gardens.size) {
                if (garden_temp.equals(gardens[i].gardenCode, true)) {
                    garden_name.error = getString(R.string.error_garden_exist)
                    return false
                }
            }
        }
        return true
    }

    /**
     * This method is to batch name
     */
    private fun checkGardenImage(check: ByteArray): Boolean {
        if (check.isEmpty()) {
            Toast.makeText(this, R.string.image_no_select_vi, Toast.LENGTH_LONG).show()
            return false
        }
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
            selected_garden_image.setImageBitmap(bitmap)
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            try {
//                val inputStream = contentResolver.openInputStream(uri!!)
//                val bitmap = BitmapFactory.decodeStream(inputStream)
                Glide.with(this)
                    .load(uri)
                    .into(selected_garden_image)

                //selected_image.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun generateAssetTypeCode(garden_name: String): String {
        var result: String = ""

        if (garden_name.isNotBlank()) {
            var temp: String = Normalizer.normalize(garden_name, Normalizer.Form.NFD)
            var pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
            var garden_name_en = pattern.matcher(temp).replaceAll("")

            result = garden_name_en.trim().replace(" ", "").toUpperCase()
        }
        return result
    }


    /**
     * the method to display batch
     */
    fun getListGarden(): ArrayList<Garden> {
        database = Database(activity)
        gardens = database.findAllGarden()
        if (gardens.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách khu vườn đang trống !", Toast.LENGTH_LONG).show()
        }
        return gardens
    }

}