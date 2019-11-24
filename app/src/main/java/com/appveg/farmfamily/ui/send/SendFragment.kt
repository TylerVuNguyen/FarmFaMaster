package com.appveg.farmfamily.ui.send


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

import androidx.fragment.app.Fragment

import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.garden.GardenCustom


class SendFragment : Fragment() {
    private lateinit var sendViewModel: SendViewModel

    private lateinit var database: Database

    var gardenList: ArrayList<GardenCustom> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_send, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_qlkv)
        gardenList = getAllGardens()

        grid.adapter = this.activity?.let { GardenOfQtyApdapter(it, gardenList) }



        grid.setOnItemClickListener { adapterView, view, i, l ->
            var intent: Intent = Intent(this.context, ChiTietDotSanLuongActivity::class.java)
            var id: Int? = gardenList.get(i).gardenId
            intent.putExtra("garden_id", id)
            startActivity(intent)
        }
        return root.rootView
    }

    //list khu vuon
    private fun getAllGardens(): ArrayList<GardenCustom> {
        database = Database(activity)
        gardenList = database.findAllGarden()
        return gardenList
    }
}










