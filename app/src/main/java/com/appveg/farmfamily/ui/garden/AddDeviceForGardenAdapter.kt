package com.appveg.farmfamily.ui.garden

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.device.Device


class AddDeviceForGardenAdapter (private var activity: Activity, private var items: ArrayList<Device>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var lblName: TextView? = null
        var imgDevice: ImageView? = null

        init {
            this.lblName = row?.findViewById<TextView>(R.id.lbl_name)

            this.imgDevice = row?.findViewById<ImageView>(R.id.img_device_kv)

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_item_device_for_garden, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var device = items[position]
        viewHolder.lblName?.text = device.deviceName

        viewHolder.imgDevice?.setImageResource(device.deviceImg!!)

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