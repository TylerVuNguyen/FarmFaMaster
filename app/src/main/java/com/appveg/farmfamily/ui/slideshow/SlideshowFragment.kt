package com.appveg.farmfamily.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.KhuVuon
import com.appveg.farmfamily.ui.home.KhuVuonAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_device)
        var kvList = this.generateCompanyData()

        grid.adapter = this.activity?.let { KhuVuonAdapter (it, kvList) }

        grid.setOnItemClickListener { adapterView, view, i, l ->
            if(kvList.get(i).khuvuon_id == 1){

                Toast.makeText(this.activity, " Selected khu vuon is = "+ kvList.get(i).khuvuon_name , Toast.LENGTH_SHORT).show()

                var  intent: Intent  = Intent(this.context, DeviceDetailActivity::class.java)
                startActivity(intent)
            }
        }



        return root.rootView
    }





    //list khu vuon
    private fun generateCompanyData(): ArrayList<KhuVuon> {
        var result = ArrayList<KhuVuon>()
        var kv: KhuVuon = KhuVuon()
        kv.khuvuon_id = 1
        kv.khuvuon_name = "Khu vườn 1"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 2
        kv.khuvuon_name = "Khu vườn 2"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 3
        kv.khuvuon_name = "Khu vườn 3"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        kv = KhuVuon()
        kv.khuvuon_id = 4
        kv.khuvuon_name = "Khu vườn 4"
        kv.khuvuon_photo = R.drawable.kv2
        result.add(kv)

        return result
    }


}