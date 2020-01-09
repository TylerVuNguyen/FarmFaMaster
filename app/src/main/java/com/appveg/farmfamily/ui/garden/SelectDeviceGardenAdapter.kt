package com.appveg.farmfamily.ui.garden

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetail


class SelectDeviceGardenAdapter (private var activity: Activity, private var items: ArrayList<DeviceDetail>,private var gardenId : Int,private var countDefault :Int) :  BaseAdapter() {
    private lateinit var database: Database
    private var deviceStatus: Boolean = false
    private var count : Int = 0
    private class ViewHolder(row: View?) {
        var imgDeviceForGarden: ImageView? = null
        var deviceForGardenChecked: CheckBox
        init {
            this.imgDeviceForGarden = row?.findViewById(R.id.img_device_for_garden)
            this.deviceForGardenChecked = row?.findViewById(R.id.device_for_garden_checked) as CheckBox
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_device_for_garden, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var deviceDetailForGarden = getItem(position)

        // chuyển bytearray về bitmap để hiển thị
        var imageBitmap: ByteArray? = deviceDetailForGarden.deviceDetailImg
        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageBitmap, 0, imageBitmap!!.size)
        viewHolder.imgDeviceForGarden!!.setImageBitmap(bitmap)

        var deviceCountSelect: TextView = activity.findViewById(R.id.count_select_device)

        viewHolder.deviceForGardenChecked.isChecked = checked(deviceDetailForGarden.deviceDetailStatus!!)

        viewHolder.deviceForGardenChecked.setOnClickListener {
            setCount()
            deviceStatus = viewHolder.deviceForGardenChecked.isChecked
            var temp = updateStatus(gardenId,deviceDetailForGarden.deviceDetailID!!,deviceStatus)
            notice(temp,deviceStatus)
            viewHolder.deviceForGardenChecked.isChecked = deviceStatus
            deviceCountSelect.text = countSelected(deviceStatus)
        }

        return view
    }

    override fun getItem(i: Int): DeviceDetail {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    // checked load default (case broken)
    private fun checked(status: String): Boolean{
        var result = false
        if("Y" == status.trim()){
            result = true
        }
        return result
    }
    // count selected
    private fun countSelected(deviceStatus: Boolean) : String{
        var result = ""
        if(!deviceStatus && count != 0){
            count--
        }else if (deviceStatus){
            count++
        }
        result = "Đã thêm ($count) thiết bị"

        return result
    }

    private fun setCount(){
        if(countDefault != null && countDefault != 0 ){
            count = countDefault
        }
    }

    // display message settings
    private fun notice(temp : Int,status : Boolean){
        if(temp != null && status){
            Toast.makeText(activity,"Đã cài đặt thiết bị",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity,"Đã gở cài đặt thiết bị",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStatus(gardenId: Int,id: Int,checked: Boolean) : Int{
        database = Database(activity)
        var deviceDetail1 : DeviceDetail = DeviceDetail()
        if(checked){
            deviceDetail1.deviceDetailID = id
            deviceDetail1.gardenDetailId = gardenId
            deviceDetail1.deviceDetailStatus = "Y"
        }else{
            deviceDetail1.deviceDetailID = id
            deviceDetail1.gardenDetailId = -1
            deviceDetail1.deviceDetailStatus = "N"
        }
        return database.updateDeviceForGarden(deviceDetail1)
    }
}