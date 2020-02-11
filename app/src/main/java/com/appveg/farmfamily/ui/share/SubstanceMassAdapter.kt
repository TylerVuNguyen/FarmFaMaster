package com.appveg.farmfamily.ui.share

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.device.Device


class SubstanceMassAdapter (private var activity: Activity, private var items: ArrayList<Device>) :  BaseAdapter(){

    private class ViewHolder(row: View?) {
        var substanceImage: ImageView
        var substanceName: TextView
        var substanceNum: TextView

        init {
            this.substanceImage = row?.findViewById(R.id.view_substance_image) as ImageView
            this.substanceName = row?.findViewById(R.id.view_substance_name) as TextView
            this.substanceNum = row?.findViewById(R.id.view_substance_num) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_substance_mass, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var device = items[position]

//        viewHolder.deviceName.text = device.deviceName
//        viewHolder.deviceNum.text = device.deviceNum
//
//        // chuyển bytearray về bitmap để hiển thị
//        var imageBitmap : ByteArray? = device.deviceImg
//        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)
//        viewHolder.imgDevice.setImageBitmap(bitmap)

        return view
    }
    override fun getItem(i: Int): Device {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }

}