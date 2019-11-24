package com.appveg.farmfamily.ui.garden

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.GridView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.appveg.farmfamily.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        var grid = root.findViewById<GridView>(R.id.fragment_qlkv)
        var listGarden = this.listGarden()

        grid.adapter = this.activity?.let {
            QLKVAdapter(
                it,
                listGarden
            )
        }

        grid.setOnItemClickListener { adapterView, view, i, l -> Toast.makeText(this.activity, " Selected QLKV is = " + listGarden.get(i).gardenId, Toast.LENGTH_SHORT).show() }


        //icon them
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Them ", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()

            var  intent: Intent  = Intent(this.context, ThemKhuVuonActivity::class.java)
            startActivity(intent)
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
//            0 -> this.editQLKV(info)
            0->  {var  intent: Intent  = Intent(this.context, SuaKhuVuonActivity::class.java)
            startActivity(intent)}
//            0 -> Toast.makeText(this.requireContext().applicationContext, "sua", Toast.LENGTH_SHORT).show()
//            //delete


//            1 -> this.deleteQLKV(info)
            1 -> Toast.makeText(this.requireContext().applicationContext, "Xoá khu vườn" , Toast.LENGTH_SHORT).show()
////            2 -> this.showGraphic(info)
        }
        return true
    }


    private fun listGarden(): ArrayList<Garden> {
        var result = ArrayList<Garden>()
        var garden: Garden = Garden()
        garden.gardenId = 1
        garden.gardenName = "Khu vườn 1"
        garden.gardenImage = R.drawable.kv2
        result.add(garden)

        garden = Garden()
        garden.gardenId = 2
        garden.gardenName = "Khu vườn 2"
        garden.gardenImage = R.drawable.kv2
        result.add(garden)

        garden = Garden()
        garden.gardenId = 3
        garden.gardenName = "Khu vườn 3"
        garden.gardenImage = R.drawable.kv2
        result.add(garden)

        garden = Garden()
        garden.gardenId = 4
        garden.gardenName = "Khu vườn 4"
        garden.gardenImage = R.drawable.kv2
        result.add(garden)

        return result
    }

}