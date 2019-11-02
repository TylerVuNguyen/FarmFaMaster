package com.appveg.farmfamily.ui.send



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

import android.widget.Toast
import androidx.fragment.app.Fragment

import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.KhuVuon
import com.appveg.farmfamily.ui.home.KhuVuonAdapter


class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_send, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_qlkv)
        var kvList = this.generateCompanyData()

        grid.adapter = this.activity?.let { KhuVuonAdapter (it, kvList) }



        grid.setOnItemClickListener { adapterView, view, i, l ->
            if(kvList.get(i).khuvuon_id == 1){
             var  intent: Intent  = Intent(this.context, ChiTietDotSanLuongActivity::class.java);
                startActivity(intent);
                Toast.makeText(this.activity, " Selected khu vuon is = "+ kvList.get(i).khuvuon_name , Toast.LENGTH_SHORT).show()

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









