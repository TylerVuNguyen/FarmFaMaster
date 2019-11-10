package com.appveg.farmfamily.ui.tools

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog


//class GardenAdapter (var context: Context, var mang: ArrayList<Garden>) : BaseAdapter() {

//    class ViewHolder(row: View) {
//        var imageroom: ImageView
//        var nameTextView: TextView
//        //        var viewroom_number: TextView
//        var iconDelete: ImageView
//        var swtichIsActive: Switch
//
//        init {
//            imageroom = row.findViewById(R.id.viewroom_imageIconRoom)
//            nameTextView = row.findViewById(R.id.viewroom_nameRoom)
////            viewroom_number = row.findViewById(R.id.viewroom_number)
//            iconDelete = row.findViewById(R.id.viewroom_icondelete)
//            swtichIsActive = row.findViewById(R.id.viewlistroom_isActive)
//        }
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var view: View?
//        var viewHolder: ViewHolder
//        if (convertView == null) {
//            //chua co gia tri tren view
//            var layoutInflater = LayoutInflater.from(context)
//            view = layoutInflater.inflate(R.layout.a_row_viewroom, null)
//            viewHolder = ViewHolder(view)
//            view.tag = viewHolder
//
//        } else {
//            //lan 2 truy cap
//            view = convertView
//            viewHolder = convertView.tag as ViewHolder
//        }
//        //set giao dien
//        var room: Room = getItem(position) as Room
//        //hinh anh cho room
//
//        //chuyn byte[] => bitmap
//        var hinhanh: ByteArray = room.HandleImageBLOB
//        val bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.size)
//        viewHolder.imageroom.setImageBitmap(bitmap)
//
//        viewHolder.nameTextView.text = room.HandleName//ten phong
////        viewHolder.viewroom_number.text = (position + 1).toString()//stt
//
//        viewHolder.iconDelete.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //xac nhan xoa hay la khong
//                lateinit var dialog: AlertDialog
//                // Initialize a new instance of alert dialog builder object
//                val builder = AlertDialog.Builder(context)
//                // Set a title for alert dialog
//                builder.setTitle("Xóa phòng tên: ${room.HandleName}")
//                // Set a message for alert dialog
//                builder.setMessage("Các thiết bị trong phòng thành vô gia cư nhaaa!!!")
//                val dialogClickListener = DialogInterface.OnClickListener { _, which ->
//                    when (which) {
//                        DialogInterface.BUTTON_POSITIVE -> {
//                            val database: DatabaseDEMO = DatabaseDEMO(context)
//                            val check: Boolean = database.deleteRoom(room.HandleID)
//                            if (check) {
//                                val intent = Intent(context, ViewListRoomActivity::class.java)
//                                intent.putExtra("deleteroom", room.HandleID)
//                                context.startActivity(intent)
//                                (context as Activity).finish()
//                            } else {
//                                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                        }
//                        DialogInterface.BUTTON_NEGATIVE -> {
////                            Toast.makeText(context, "Khoong xoa ", Toast.LENGTH_SHORT).show()
//                            dialog.dismiss()
//                        }
//                    }
//                }
////                 Set the alert dialog positive/yes button
//                builder.setPositiveButton("Xóa", dialogClickListener)
//                // Set the alert dialog negative/no button
//                builder.setNegativeButton("Hủy", dialogClickListener)
//                // Initialize the AlertDialog using builder object
//                dialog = builder.create()
//                // Finally, display the alert dialog
//                dialog.show()
//            }
//        })
//
//        view!!.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
////                Toast.makeText(context, "Hien thi chi tiet", Toast.LENGTH_LONG).show()
//                //chuyen toi activity hien thi chi tiet cai phong
//                val intent = Intent(context, DetailRoom::class.java)
//                intent.putExtra("_idRoom", room.HandleID)
//                context.startActivity(intent)
//                (context as Activity).finish()
//            }
//        })
//
//        viewHolder.swtichIsActive.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Toast.makeText(context, "Không hoạt động!", Toast.LENGTH_LONG).show()
//            }
//
//        })
//
//        return view as View
//    }
//
//    // Extension function to show toast message
//    private fun Context.toast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun getItem(position: Int): Any {
//        return mang[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return mang.size
//    }
//}