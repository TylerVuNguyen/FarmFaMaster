package com.appveg.farmfamily.ui.send

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R

class ChiTietAdapter (private val context: Context, private val dotRau: ArrayList<DotRau>) : BaseAdapter() {

    fun remove(position: Int) {
        dotRau.removeAt(position)
        notifyDataSetChanged()
    }

    //1
    override fun getCount(): Int {
        return dotRau.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dotRau[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    private class ViewHolder(row: View?) {
        var dotRau_name: TextView
        var img_dotRau: ImageView



        init {
            this.dotRau_name = row?.findViewById(R.id.dotRau_name) as TextView
            this.img_dotRau = row?.findViewById(R.id.img_dotRau) as ImageView


        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View?
        var viewHolder : ViewHolder
        if( convertView == null){

            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.layoutlistview_chitietdot_sanluong,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var dotRau : DotRau = getItem(position) as DotRau
        viewHolder.dotRau_name.text = dotRau.dotRau_name
        viewHolder.img_dotRau.setImageResource(dotRau.dotRau_photo!!)

        return view as View

    }





}