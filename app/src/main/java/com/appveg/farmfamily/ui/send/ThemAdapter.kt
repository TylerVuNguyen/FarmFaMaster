package com.appveg.farmfamily.ui.send

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.appveg.farmfamily.R

class ThemAdapter (private val context: Context, private val dotRau: ArrayList<Rau>) : BaseAdapter() {

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
        var rau_name: TextView
        var rau_soluong: TextView



        init {
            this.rau_name = row?.findViewById(R.id.txt_rau) as TextView
            this.rau_soluong = row?.findViewById(R.id.txtsoluong) as TextView


        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View?
        var viewHolder : ViewHolder
        if( convertView == null){

            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.list_itemrau_themsl,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var rau : Rau = getItem(position) as Rau
        viewHolder.rau_name.text = rau.rau_name
        viewHolder.rau_soluong.text = rau.rau_soluong.toString()

        return view as View

    }


}