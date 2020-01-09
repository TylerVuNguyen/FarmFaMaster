package com.appveg.farmfamily.ui.garden

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.device.DeviceDetail
import com.appveg.farmfamily.ui.vegetable.Vegetable


class SelectVegGardenAdapter (private var activity: Activity, private var items: ArrayList<Vegetable>) :  BaseAdapter() {
    private class ViewHolder(row: View?) {
        var imgVegForGarden: ImageView? = null

        init {
            this.imgVegForGarden = row?.findViewById(R.id.img_veg_for_garden)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_veg_for_garden, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var vegetableForGarden = items[position]

        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap: ByteArray? = vegetableForGarden.vegImgBlob
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap, 0, imageBitmap!!.size)
        viewHolder.imgVegForGarden!!.setImageBitmap(bitmap)

        return view
    }

    override fun getItem(i: Int): Vegetable {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}