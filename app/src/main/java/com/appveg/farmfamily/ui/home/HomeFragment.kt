package com.appveg.farmfamily.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.garden.GardenCustom

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //hien thi grid layout
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        var grid = root.findViewById<GridView>(R.id.fragment_khuvuon)
        var kvList = this.generateCompanyData()

        grid.adapter = this.activity?.let {KhuVuonAdapter (it, kvList) }

        grid.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this.activity, " Selected Company is = "+ kvList.get(i).gardenName , Toast.LENGTH_SHORT).show()
        }
        return root.rootView

    }

    private fun generateCompanyData(): ArrayList<GardenCustom> {
        var result = ArrayList<GardenCustom>()
        var kv:  GardenCustom = GardenCustom()
        kv.gardenId = 1
        kv.gardenName = "Khu vườn 1"
        kv.gardenImage = R.drawable.kv2
        result.add(kv)

        kv = GardenCustom()
        kv.gardenId = 2
        kv.gardenName = "Khu vườn 2"
        kv.gardenImage = R.drawable.kv2
        result.add(kv)

        kv = GardenCustom()
        kv.gardenId = 3
        kv.gardenName = "Khu vườn 3"
        kv.gardenImage = R.drawable.kv2
        result.add(kv)

        kv = GardenCustom()
        kv.gardenId = 4
        kv.gardenName = "Khu vườn 4"
        kv.gardenImage = R.drawable.kv2
        result.add(kv)

        return result
    }
}