package com.appveg.farmfamily.ui.home

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.garden.GardenCustom

class KhuVuonAdapter(private var activity: Activity, private var items: ArrayList<GardenCustom>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var kvName: TextView? = null
        var kvCamera: ImageView? = null

        init {
            this.kvName = row?.findViewById(R.id.name_kv)
            this.kvCamera = row?.findViewById(R.id.img_kv)
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_khuvuon, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var garden = items[position]
        viewHolder.kvName?.text = garden.gardenName
        viewHolder.kvCamera?.setImageResource(garden.gardenImage!!)

        return view
    }
    override fun getItem(i: Int): GardenCustom {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}