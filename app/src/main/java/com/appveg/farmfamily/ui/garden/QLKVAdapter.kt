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

class QLKVAdapter (private var activity: Activity, private var items: ArrayList<Garden>) :  BaseAdapter(){

    private class ViewHolder(row: View?) {
        var qlkhuvuon_name: TextView? = null
        var qlkhuvuon_photo: ImageView? = null

        init {
            this.qlkhuvuon_name = row?.findViewById<TextView>(R.id.kv_qlkvname)
            this.qlkhuvuon_photo = row?.findViewById<ImageView>(R.id.img_qlkv)
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_ql_khuvuon, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var khuVuon = items[position]
        viewHolder.qlkhuvuon_name?.text = khuVuon.gardenName
        viewHolder.qlkhuvuon_photo?.setImageResource(khuVuon.gardenImage!!)

        return view as View
    }
    override fun getItem(i: Int): Garden {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}