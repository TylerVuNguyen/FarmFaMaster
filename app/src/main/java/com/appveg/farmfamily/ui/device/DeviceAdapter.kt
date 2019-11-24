package com.appveg.farmfamily.ui.device

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R

class DeviceAdapter (private var activity: Activity, private var items: ArrayList<Device>) :  BaseAdapter(){

    private class ViewHolder(row: View?) {
        var device_name: TextView? = null
        var img_device: ImageView? = null


        init {
            this.device_name = row?.findViewById<TextView>(R.id.viewdevice_nameDevice)
            this.img_device = row?.findViewById<ImageView>(R.id.viewdevice_imageIconDevice)


        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_device, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var device = items[position]
        viewHolder.device_name?.text = device.deviceName
        viewHolder.img_device?.setImageResource(device.deviceImg!!)

        return view as View
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