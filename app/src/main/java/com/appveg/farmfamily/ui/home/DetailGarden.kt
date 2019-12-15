package com.appveg.farmfamily.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.chart.ChartActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_garden.*

class DetailGarden : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_garden)

        actionButton()
        gardenInfo()


    }
    // action
    fun actionButton(){
        thongkekhuvuon.setOnClickListener {
            var intent : Intent = Intent(this , ChartActivity::class.java)
            var id: String? = getDataFromItent()
            intent.putExtra("garden_code", id)
            startActivity(intent)
        }
    }
    /**
     * the method to gardenInfo
     */
    fun gardenInfo(){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()
        database.child(garden).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                var KHUVUON1 : DetailGardenFirebase? = dataSnapshot.getValue(DetailGardenFirebase::class.java)
                temperature.text = KHUVUON1?.Temperature
                humidity.text = KHUVUON1?.Humidity
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent() : String {
        val bundle:Bundle = intent.extras
        val id: String =
            bundle.get("garden_code") as String
        return id

    }
}
