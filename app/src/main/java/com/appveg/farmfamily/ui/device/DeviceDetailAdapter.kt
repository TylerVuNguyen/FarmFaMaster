package com.appveg.farmfamily.ui.device

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import kotlinx.android.synthetic.main.activity_device_detail_status.*
import kotlinx.android.synthetic.main.layout_device_detail.*
import java.util.*
import kotlin.collections.ArrayList

class DeviceDetailAdapter (private var activity: Activity, private var items: ArrayList<DeviceDetail>) :  BaseAdapter(){

    private lateinit var database: Database

    private class ViewHolder(row: View?) {
        var deviceDetailImage: ImageView? = null
        var deviceDetailStatus: TextView
        var deviceDetailCode: TextView
        var deviceDetailStatusChecked: CheckBox

        init {
            this.deviceDetailImage = row?.findViewById(R.id.view_device_detail_image) as ImageView
            this.deviceDetailStatus = row?.findViewById(R.id.view_device_detail_status) as TextView
            this.deviceDetailCode = row?.findViewById(R.id.view_device_detail_code) as TextView
            this.deviceDetailStatusChecked = row?.findViewById(R.id.device_checked) as CheckBox
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_device_detail, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var deviceDetail = items[position]
        viewHolder.deviceDetailStatus.text = deviceDetail.deviceDetailStatus
        viewHolder.deviceDetailCode.text = deviceDetail.deviceDetailCode
        viewHolder.deviceDetailStatusChecked.setOnClickListener {
            var deviceStatus = viewHolder.deviceDetailStatusChecked.isChecked
            updateStatus(deviceStatus,deviceDetail.deviceDetailID!!)
            viewHolder.deviceDetailStatusChecked.isChecked = deviceStatus
            viewHolder.deviceDetailStatus.text = convertChecked(deviceStatus)
        }
        viewHolder.deviceDetailStatusChecked.isChecked = checked(deviceDetail.deviceDetailStatus!!)



        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap : ByteArray? = deviceDetail.deviceDetailImg
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)
        viewHolder.deviceDetailImage!!.setImageBitmap(bitmap)

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
    private fun checked(status: String): Boolean{
        var result: Boolean = false
        if("Đã hư hỏng" == status.trim()){
         result = true
        }
        return result
    }

    private fun convertChecked(status: Boolean): String {
        var result = ""
        if(status){
            result = "Đã hư hỏng"
        }else{
            result = "Chưa sử dụng"
        }
        return result
    }

    private fun updateStatus(checked: Boolean,id: Int){
        database = Database(activity)
        var deviceDetail1 : DeviceDetail = DeviceDetail()
        if(checked){
            deviceDetail1.deviceDetailID = id
            deviceDetail1.deviceDetailStatus = "B"
        }else{
            deviceDetail1.deviceDetailID = id
            deviceDetail1.deviceDetailStatus = "N"
        }
        database.updateDeviceDetailStatus(deviceDetail1)

    }

}