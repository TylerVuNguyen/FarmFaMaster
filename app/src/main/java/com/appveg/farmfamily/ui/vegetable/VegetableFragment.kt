package com.appveg.farmfamily.ui.vegetable

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView

class VegetableFragment  : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_vegetable, container, false)

        val listVeg = listVegetable()

        var listviewgarden = root.findViewById(R.id.list_view_vegetable) as SwipeMenuListView
//        var listviewgarden = root.findViewById(R.id.list_view_vegetable) as ListView
//
        listviewgarden.adapter = this.activity?.let { VegetableFragmentAdapter(it, listVeg) }
//
//        listviewgarden.adapter = VegetableFragmentAdapter(requireContext(), listVeg!!)
//
        listviewgarden.setOnItemClickListener { adapterView, view, i, l ->
//            if (listVeg.get(i).vegID == 1) {
//                var intent: Intent = Intent(requireContext(), ChiTietSanLuongRauActivity::class.java);
//                startActivity(intent);
//            }
            Toast.makeText(requireContext(),"ahihi", Toast.LENGTH_SHORT).show()

        }


        //swipemenulistview
        val creator = SwipeMenuCreator { menu ->
            // create "open" item
            val editItem = SwipeMenuItem(
                this.context
            )
//            // set item background
//            openItem.background = ColorDrawable(
//                Color.rgb(0x00, 0x66,0xff
//                )
//            )
            // set item width
            editItem.width = 100
            // set item title
//            editItem.title = "Open"
//            // set item title fontsize
//            editItem.titleSize = 18
            // set item title font color
//            editItem.titleColor = Color.WHITE

            //set icon
            editItem.setIcon(R.drawable.ic_edit)
            // add to menu
            menu.addMenuItem(editItem)

            // create "delete" item
            val deleteItem = SwipeMenuItem(
                this.context
            )
//            // set item background
//            deleteItem.background = ColorDrawable(
//                Color.rgb(
//                    0xF9,
//                    0x3F, 0x25
//                )
//            )
            // set item width
            deleteItem.width = 100
            // set a icon
            deleteItem.setIcon(R.drawable.ic_delete)
            // add to menu
            menu.addMenuItem(deleteItem)
        }

        // set swipe
        listviewgarden.setMenuCreator(creator)
        listviewgarden.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        var intent: Intent = Intent(requireContext(), EditVegetableActivity::class.java);
                        startActivity(intent)
//                        Toast.makeText(requireContext(), listVeg[position].toString(), Toast.LENGTH_LONG).show()
                    }
                    1 -> {
                        Toast.makeText(requireContext(), listVeg[position].toString(), Toast.LENGTH_LONG).show()
                    }
                }// open
                // delete
                // false : close the menu; true : not close the menu
                return false
            }
        })

        //button them rau
      var viewveg_btn_add = root.findViewById(R.id.viewveg_btn_add) as Button
        viewveg_btn_add.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var intent: Intent = Intent(requireContext(), AddVegetableActivity::class.java)
                startActivity(intent)
            }


        })

        return root.rootView


    }
    private fun listVegetable(): ArrayList<Vegetable> {
        var result = ArrayList<Vegetable>()
        var veg: Vegetable = Vegetable()
        veg.vegID = 1
        veg.vegName = "Batch xà lách "
        veg.vegImg = R.drawable.xalach.toString()
        result.add(veg)

        veg = Vegetable()
        veg.vegID = 2
        veg.vegName = "Batch cải ngọt "
        veg.vegImg = R.drawable.xalach.toString()
        result.add(veg)

        veg = Vegetable()
        veg.vegID = 3
        veg.vegName = "Cải bẹ xanh "
        veg.vegImg = R.drawable.xalach.toString()
        result.add(veg)

        veg = Vegetable()
        veg.vegID = 4
        veg.vegName = "Xúp lơ"
        veg.vegImg = R.drawable.xalach.toString()
        result.add(veg)

        return result
    }


}