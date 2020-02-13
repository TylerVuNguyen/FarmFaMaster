package com.appveg.farmfamily.ui.garden

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetail
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.Normalizer
import java.util.regex.Pattern


class SelectDeviceGardenAdapter(
    private var activity: Activity,
    private var items: ArrayList<DeviceDetail>,
    private var gardenId: Int,
    private var countDefault: Int,
    private var gardenCode: String
) : BaseAdapter() {
    private lateinit var database: Database
    private lateinit var database1: DatabaseReference
    private var deviceStatus: Boolean = false
    private var count: Int = 0

    private class ViewHolder(row: View?) {
        var imgDeviceForGarden: ImageView? = null
        var deviceForGardenChecked: CheckBox

        init {
            this.imgDeviceForGarden = row?.findViewById(R.id.img_device_for_garden)
            this.deviceForGardenChecked =
                row?.findViewById(R.id.device_for_garden_checked) as CheckBox
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_device_for_garden, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var deviceDetailForGarden = getItem(position)

        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap: ByteArray? = deviceDetailForGarden.deviceDetailImg
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap, 0, imageBitmap!!.size)
        viewHolder.imgDeviceForGarden!!.setImageBitmap(bitmap)

        var deviceCountSelect: TextView = activity.findViewById(R.id.count_select_device)

        viewHolder.deviceForGardenChecked.isChecked =
            checked(deviceDetailForGarden.deviceDetailStatus!!)

        // set count load default
        count = countDefault

        // event checked
        viewHolder.deviceForGardenChecked.setOnClickListener {
            deviceStatus = viewHolder.deviceForGardenChecked.isChecked
            var temp = updateStatus(
                gardenId,
                deviceDetailForGarden.deviceDetailID!!,
                deviceStatus,
                deviceDetailForGarden.deviceID!!,
                gardenCode
            )
            if (temp != null && temp != 0) {
                notice(temp, deviceStatus)
                viewHolder.deviceForGardenChecked.isChecked = deviceStatus
                deviceCountSelect.text = countSelected(deviceStatus)
            } else if (temp == 0) {
                viewHolder.deviceForGardenChecked.isChecked = false
            }
        }

        return view
    }

    override fun getItem(i: Int): DeviceDetail {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    // checked load default (case broken)
    private fun checked(status: String): Boolean {
        var result = false
        if ("Y" == status.trim()) {
            result = true
        }
        return result
    }

    // count selected
    private fun countSelected(deviceStatus: Boolean): String {
        var result = ""
        if (!deviceStatus && count != 0) {
            count--
        } else if (deviceStatus) {
            count++
        }
        result = "Đã thêm ($count) thiết bị"

        return result
    }


    // display message settings
    private fun notice(temp: Int, status: Boolean) {
        if (temp != null && status) {
            Toast.makeText(activity, "Đã cài đặt thiết bị", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Đã gở cài đặt thiết bị", Toast.LENGTH_SHORT).show()
        }
    }

    // update device for garden
    private fun updateStatus(
        gardenId: Int,
        id: Int,
        checked: Boolean,
        deviceId: Int,
        gardenCode: String
    ): Int {
        database = Database(activity)
        database1 = FirebaseDatabase.getInstance().reference
        var deviceDetail1 = DeviceDetail()
        var temps = database.findAllDeviceDetail(deviceId, gardenId)
        var device = database.findDeviceById(deviceId)
        var codeSS = generateAssetTypeCode(device.deviceName!!) + gardenId.toString()


        var result = false
        if (!temps.isNullOrEmpty()) {
            result = true
        }

        if (checked && !result) {
            deviceDetail1.deviceDetailID = id
            deviceDetail1.gardenDetailId = gardenId
            deviceDetail1.deviceDetailStatus = "Y"
            deviceDetail1.deviceDetailCodeSS = codeSS
            if (codeSS.contains("MAYBOMDUNGDICH", false) || codeSS.contains(
                    "BATCHEMUA",
                    false
                ) || codeSS.contains("BONGDEN", false) || codeSS.contains(
                    "MAYBOMPHUNSUONG",
                    false
                )
            ) {
                database1.child(gardenCode).child(codeSS).child("value").setValue("OFF")
            }

            Toast.makeText(activity, codeSS, Toast.LENGTH_SHORT).show()
        } else if (checked && result) {
            Toast.makeText(activity, "Thiết bị đã tồn tại", Toast.LENGTH_SHORT).show()
        } else {
            deviceDetail1.deviceDetailID = id
            deviceDetail1.gardenDetailId = -1
            deviceDetail1.deviceDetailStatus = "N"
            deviceDetail1.deviceDetailCodeSS = ""
            if (codeSS.contains("MAYBOMDUNGDICH", false) || codeSS.contains(
                    "BATCHEMUA",
                    false
                ) || codeSS.contains("BONGDEN", false) || codeSS.contains(
                    "MAYBOMPHUNSUONG",
                    false
                )
            ) {
                database1.child(gardenCode).child(codeSS).removeValue()
            }
        }

        return database.updateDeviceForGarden(deviceDetail1)
    }

    private fun generateAssetTypeCode(device_name: String): String {
        var result: String = ""

        if (device_name.isNotBlank()) {
            var temp: String = Normalizer.normalize(device_name, Normalizer.Form.NFD)
            var pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
            var gardenNameEn = pattern.matcher(temp).replaceAll("")

            result = gardenNameEn.trim().replace(" ", "").replace("đ", "d").toUpperCase()
        }
        return result
    }
}