package com.appveg.farmfamily.ui.param

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.vegetable.Vegetable

class ParamFragmentAdapter (private val activity: FragmentActivity?, private var items: ArrayList<Vegetable>) :  BaseAdapter(){

    //1
    override fun getCount(): Int {
        return items.size
    }

    //2
    override fun getItem(position: Int): Any {
        return items[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    private class ViewHolder(row: View?) {
        var vegName: TextView? = null
        var vegImg: ImageView
        var status: TextView? = null



        init {
            this.vegName = row?.findViewById(R.id.view_veg_param_name) as TextView
            this.vegImg = row?.findViewById(R.id.view_veg_param_image) as ImageView
            this.status = row?.findViewById(R.id.view_veg_param_status) as TextView
        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View?
        var viewHolder : ViewHolder
        if( convertView == null){

            var layout = LayoutInflater.from(activity)
            view = layout.inflate(R.layout.layout_veg_pram_detail,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var veg : Vegetable = getItem(position) as Vegetable
        viewHolder.vegName!!.text = veg.vegName
        viewHolder.status!!.text = getStatus(veg.paramId)

        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap : ByteArray? = veg.vegImgBlob
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap,0, imageBitmap!!.size)
        viewHolder.vegImg.setImageBitmap(bitmap)
        return view as View

    }

    private fun getStatus(paramId : Int) : String{
        var result = "Chưa cài đặt tham số"
        if(paramId != 0 ){
            result = "Đã cài đặt tham số"
        }
        return result
    }

}