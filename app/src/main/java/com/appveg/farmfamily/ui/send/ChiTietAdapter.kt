package com.appveg.farmfamily.ui.send

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.KhuVuon

class ChiTietAdapter (private val context: Context, private val rau: ArrayList<Rau>) : BaseAdapter() {
    //1
    override fun getCount(): Int {
        return rau.size
    }

    //2
    override fun getItem(position: Int): Any {
        return rau[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    private class ViewHolder(row: View?) {
        var rauName: TextView
        var rauImg: ImageView

        init {
            this.rauName = row?.findViewById(R.id.rau_name) as TextView
            this.rauImg = row?.findViewById(R.id.img_rau) as ImageView
        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View?
        var viewHolder : ViewHolder
        if( convertView == null){

            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.layoutlistview_chitiet_sanluong,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var rau : Rau = getItem(position) as Rau
        viewHolder.rauName.text = rau.rau_name
        viewHolder.rauImg.setImageResource(rau.rau_photo!!)

        return view as View

    }




}