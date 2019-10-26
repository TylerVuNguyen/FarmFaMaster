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

class KhuVuonAdapter(private var activity: Activity, private var items: ArrayList<KhuVuon>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var kvName: TextView? = null
        var kvCamera: ImageView? = null

        init {
            this.kvName = row?.findViewById<TextView>(R.id.kv_name)
            this.kvCamera = row?.findViewById<ImageView>(R.id.img_kv)
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
        var khuVuon = items[position]
        viewHolder.kvName?.text = khuVuon.khuvuon_name
        viewHolder.kvCamera?.setImageResource(khuVuon.khuvuon_photo!!)

        return view as View
    }
    override fun getItem(i: Int): KhuVuon {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}