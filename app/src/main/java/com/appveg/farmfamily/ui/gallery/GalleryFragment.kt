package com.appveg.farmfamily.ui.gallery

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.home.KhuVuonAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_chi_tiet_san_luong.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        var grid = root.findViewById<GridView>(R.id.fragment_qlkv)
        var qlkvList = this.generateQLKVData123()

        grid.adapter = this.activity?.let {QLKVAdapter (it, qlkvList) }

        grid.setOnItemClickListener { adapterView, view, i, l -> Toast.makeText(this.activity, " Selected QLKV is = " + qlkvList.get(i).qlkv_name, Toast.LENGTH_SHORT).show() }


        //icon them
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        //context menu
        registerForContextMenu(grid)

        return root.rootView

    }

    fun editQLKV ( id: Int) : Boolean{
        return true
    }

    fun deleteQLKV (id: Int) : Boolean{
        return true
    }


    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Set Header of Context Menu
        menu!!.setHeaderTitle(this.resources.getString(R.string.title_context_menu))
        menu.add(0, v.id, 0, this.resources.getString(R.string.edit))
        menu.add(0, v.id, 1, this.resources.getString(R.string.delete))

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onContextItemSelected(item: MenuItem): Boolean {

        //get menu


        //Get Order of Selected Item
        val selectedItemOrder = item!!.order
        //Get Title Of Selected Item
        val selectedItemTitle = item.title
        //xac dinh nguoi dung vua click vao button nao
        //To get Name of Person Click on ListView
        val info = item.itemId
        when(item.order){
            //edit
            0 -> this.editQLKV(info)
//            0 -> Toast.makeText(this.requireContext().applicationContext, "sua", Toast.LENGTH_SHORT).show()
//            //delete
            1 -> this.deleteQLKV(info)
//            1 -> Toast.makeText(this.requireContext().applicationContext, "xoa", Toast.LENGTH_SHORT).show()
////            2 -> this.showGraphic(info)
        }
        return true
    }


    private fun generateQLKVData123(): ArrayList<QLKhuVuon> {
        var result = ArrayList<QLKhuVuon>()
        var qlkv: QLKhuVuon = QLKhuVuon()
        qlkv.qlkv_id = 1
        qlkv.qlkv_name = "Khu vườn 1"
        qlkv.qlkv_photo = R.drawable.kv2
        result.add(qlkv)

        qlkv = QLKhuVuon()
        qlkv.qlkv_id = 2
        qlkv.qlkv_name = "Khu vườn 2"
        qlkv.qlkv_photo = R.drawable.kv2
        result.add(qlkv)

        qlkv = QLKhuVuon()
        qlkv.qlkv_id = 3
        qlkv.qlkv_name = "Khu vườn 3"
        qlkv.qlkv_photo = R.drawable.kv2
        result.add(qlkv)

        qlkv = QLKhuVuon()
        qlkv.qlkv_id = 4
        qlkv.qlkv_name = "Khu vườn 4"
        qlkv.qlkv_photo = R.drawable.kv2
        result.add(qlkv)

        return result
    }

}