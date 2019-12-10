package com.appveg.farmfamily.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.garden.Garden

class HomeFragment : Fragment() {
    private lateinit var database: Database

    private lateinit var homeViewModel: HomeViewModel

    var gardens: ArrayList<Garden> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //hien thi grid layout
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        var grid = root.findViewById<GridView>(R.id.fragment_khuvuon)
        gardens = getListGarden()

        grid.adapter = this.activity?.let {KhuVuonAdapter (it, gardens) }

        grid.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this.activity, " Selected Company is = "+ gardens.get(i).gardenName , Toast.LENGTH_SHORT).show()
        }
        return root.rootView

    }

    /**
     * the method to display batch
     */
    fun getListGarden() : ArrayList<Garden>{
        database = Database(activity)
        gardens = database.findAllGarden()
        if (gardens.isNullOrEmpty()) {
            Toast.makeText(activity, "Dánh sách khu vườn đang trống !", Toast.LENGTH_LONG).show()
        }
        return gardens
    }
}