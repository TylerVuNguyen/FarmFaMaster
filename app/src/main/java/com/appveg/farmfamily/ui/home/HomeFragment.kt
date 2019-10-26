package com.appveg.farmfamily.ui.home

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

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_khuvuon)
        var kvList = this.generateCompanyData()

        grid.adapter = this.activity?.let {KhuVuonAdapter (it, kvList) }

        grid.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this.activity, " Selected Company is = "+ kvList.get(i).khuvuon_name , Toast.LENGTH_SHORT).show()
        }
        return root.rootView
    }

    private fun generateCompanyData(): ArrayList<KhuVuon> {
        var result = ArrayList<KhuVuon>()
        var kv:  KhuVuon = KhuVuon()
        kv.khuvuon_id = 1
        kv.khuvuon_name = "apple"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 2
        kv.khuvuon_name = "Samsung"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 3
        kv.khuvuon_name = "Sony"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 4
        kv.khuvuon_name = "Redmi"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        return result
    }
}