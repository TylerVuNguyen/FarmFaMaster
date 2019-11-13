package com.appveg.farmfamily.ui.vegetable

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File

class VegetableFragmentAdapter(private val context: Context, private val veg: ArrayList<Vegetable>) : BaseAdapter() {

    fun remove(position: Int) {
        veg.removeAt(position)
        notifyDataSetChanged()
    }

    //1
    override fun getCount(): Int {
        return veg.size
    }

    //2
    override fun getItem(position: Int): Any {
        return veg[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    private class ViewHolder(row: View?) {
        var veg_Name: TextView
        var veg_Img: ImageView



        init {
            this.veg_Name = row?.findViewById(R.id.viewgarden_nameGarden) as TextView
            this.veg_Img = row?.findViewById(R.id.viewgarden_imageIconGarden) as ImageView

        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View?
        var viewHolder : ViewHolder
        if( convertView == null){

            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.layout_listview_garden,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var veg : Vegetable = getItem(position) as Vegetable
        viewHolder.veg_Name.text = veg.vegName
//        viewHolder.veg_Img.setImageResource(veg.vegImg.toString())

        Glide.with(context)
            .load(Uri.fromFile(File(veg.HandleImageVeg)))
            .apply(RequestOptions().override(100, 100))
            .into(viewHolder.veg_Img)

        return view as View

    }


}