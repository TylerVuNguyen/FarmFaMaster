package com.appveg.farmfamily.ui.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.garden.Garden
import com.appveg.farmfamily.ui.send.ChiTietDotSanLuongActivity
import com.appveg.farmfamily.ui.send.GardenOfQtyApdapter

class ShareFragment : Fragment() {

    private lateinit var shareViewModel: ShareViewModel

    private lateinit var database: Database

    var gardenList: ArrayList<Garden> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shareViewModel =
            ViewModelProviders.of(this).get(ShareViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_substance_mass)
        gardenList = getAllGardens()

        grid.adapter = this.activity?.let { GardenForMassApdapter(it, gardenList) }



        grid.setOnItemClickListener { adapterView, view, i, l ->
            var intent: Intent = Intent(this.context, ChiTietDotSanLuongActivity::class.java)
            var id: Int? = gardenList[i].gardenId
            intent.putExtra("garden_id", id)
            startActivity(intent)
        }

        return root
    }

    //list khu vuon
    private fun getAllGardens(): ArrayList<Garden> {
        database = Database(activity)
        gardenList = database.findAllGarden()
        if (gardenList.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách khu vườn đang trống !", Toast.LENGTH_LONG).show()
        }
        return gardenList
    }
}