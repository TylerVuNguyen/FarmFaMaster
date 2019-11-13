package com.appveg.farmfamily.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.TypeDevice
import com.appveg.farmfamily.ui.home.KhuVuonAdapter

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        var grid = root.findViewById<GridView>(R.id.fragment_device)
        var kvList = this.listTypeDevice()

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
    private fun listTypeDevice(): ArrayList<TypeDevice> {
        var result = ArrayList<TypeDevice>()
        var typeDevice: TypeDevice = TypeDevice()
        typeDevice.khuvuon_id = 1
        typeDevice.khuvuon_name = "Khu vườn 1"
        typeDevice.khuvuon_photo = R.drawable.kv2
        result.add(typeDevice)

        typeDevice = TypeDevice()
        typeDevice.khuvuon_id = 2
        typeDevice.khuvuon_name = "Khu vườn 2"
        typeDevice.khuvuon_photo = R.drawable.kv2
        result.add(typeDevice)

        typeDevice = TypeDevice()
        typeDevice.khuvuon_id = 3
        typeDevice.khuvuon_name = "Khu vườn 3"
        typeDevice.khuvuon_photo = R.drawable.kv2
        result.add(typeDevice)

        typeDevice = TypeDevice()
        typeDevice.khuvuon_id = 4
        typeDevice.khuvuon_name = "Khu vườn 4"
        typeDevice.khuvuon_photo = R.drawable.kv2
        result.add(typeDevice)

        return result
    }


}