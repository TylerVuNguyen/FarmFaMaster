package com.appveg.farmfamily.ui.device

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.appveg.farmfamily.R

class AddDeviceAdapter (private var activity: Activity, private var items: ArrayList<Device>) :  BaseAdapter(){

    private class ViewHolder(row: View?) {

        var img_device: ImageView? = null


        init {
            this.img_device = row?.findViewById(R.id.add_device_grid)


        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_add_device, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var device = items[position]

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