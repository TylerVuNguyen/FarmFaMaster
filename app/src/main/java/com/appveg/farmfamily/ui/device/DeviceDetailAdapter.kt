package com.appveg.farmfamily.ui.device

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R

class DeviceDetailAdapter (private var activity: Activity, private var items: ArrayList<DeviceDetail>) :  BaseAdapter(){

    private class ViewHolder(row: View?) {
        var deviceDetailImage: ImageView
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
        viewHolder.deviceDetailStatusChecked.isChecked = checked(deviceDetail.deviceDetailStatus!!)
        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap : ByteArray? = deviceDetail.deviceDetailImg
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)
        viewHolder.deviceDetailImage.setImageBitmap(bitmap)

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

    private fun checked(status: String): Boolean{
        var result: Boolean = false
        if("Đã hư hỏng" == status.trim()){
         result = true
        }
        return result
    }



}