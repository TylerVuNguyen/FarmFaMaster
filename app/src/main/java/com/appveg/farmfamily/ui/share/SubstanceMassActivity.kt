package com.appveg.farmfamily.ui.share

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import kotlinx.android.synthetic.main.activity_substance_mass.*

class SubstanceMassActivity : AppCompatActivity() {
    var activity = this@SubstanceMassActivity

    private lateinit var database: Database

    var substances: ArrayList<Substance> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_substance_mass)
        substances = getListSubstance()
        list_view_substance.adapter = SubstanceMassAdapter(activity, substances)


        list_view_substance.setOnItemClickListener { adapterView, view, i, l ->
            var gardenId = substances[i].gardenId!!
            var substanceMassId = substances[i].substanceMassId
            var intent: Intent = Intent(activity, SubstanceMassDetailActivity::class.java)
            intent.putExtra("garden_id",gardenId)
            intent.putExtra("substance_mass_id",substanceMassId)
            startActivity(intent)
        }

        view_substance_btn_add.setOnClickListener {
            var gardenId = getDataFromItent()
            var intent: Intent = Intent(activity, AddSubstanceMassActivity::class.java)
            intent.putExtra("garden_id",gardenId)
            startActivity(intent)
        }
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("garden_id") as Int
    }


    /**
     * This method is to get list category
     */
    private fun getListSubstance(): ArrayList<Substance> {
        database = Database(activity)
        var gardenId = getDataFromItent()
        var substanceList = database.findAllSubstanceByGardenId(gardenId)
        if (substanceList.isNullOrEmpty()) {
            Toast.makeText(activity, getString(R.string.substance_empty_vi), Toast.LENGTH_LONG).show()
        }
        return substanceList
    }

    /**
     * the method to resume ( call when back stack)
     */
    override fun onResume() {
        super.onResume()
        substances = getListSubstance()
        list_view_substance.adapter = SubstanceMassAdapter(activity, substances)
    }




}
