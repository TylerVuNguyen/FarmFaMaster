package com.appveg.farmfamily.ui.home

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.chart.ChartActivity
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.Device
import com.appveg.farmfamily.ui.device.DeviceDetail
import com.appveg.farmfamily.ui.garden.DeviceForGardenAdapter
import com.appveg.farmfamily.ui.garden.VegForGardenAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_device_for_garden.*
import kotlinx.android.synthetic.main.activity_detail_garden.*
import kotlinx.android.synthetic.main.layout_device_home_info.*
import kotlinx.android.synthetic.main.layout_device_home_info_1.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class DetailGardenActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database1: Database

    var devices: ArrayList<DeviceDetail> = ArrayList()
    private val activity = this@DetailGardenActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_garden)
        rainAlert()
        actionButton()

        handlingProcess()

    }

    // action
    fun actionButton() {
        thongkekhuvuon.setOnClickListener {
            var intent: Intent = Intent(this, ChartActivity::class.java)
            var id: String? = getDataFromItent()
            intent.putExtra("garden_code", id)
            startActivity(intent)
        }
    }

    /**
     * the method to get list device info
     */
    private fun getListDeviceInfo(): ArrayList<DeviceDetail> {
        var gardenId = getDataFromItentGardenId()
        database1 = Database(activity)
        var temps = database1.findAllDeviceByGardenIdForInfo(gardenId)
        if (temps.isNullOrEmpty()) {
            Toast.makeText(
                activity,
                getString(R.string.error_empty_device_home_info),
                Toast.LENGTH_SHORT
            )
        }
        return temps
    }


    private fun handlingProcess(){
        devices = getListDeviceInfo()
        if(!devices.isNullOrEmpty()){
            for(item in 0 until devices.size){
                if(devices[item].deviceDetailCodeSS!!.contains("CAMBIENNHIETDO",false)){
                    tempSenSor(devices[item].deviceDetailCodeSS!!)
                }else if (devices[item].deviceDetailCodeSS!!.contains("CAMBIENDOAM",false)){
                    humSenSor(devices[item].deviceDetailCodeSS!!)
                }else if (devices[item].deviceDetailCodeSS!!.contains("CAMBIENMUA",false)){
                    gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                }

            }
        }

    }


    /**
     * the method to gardenInfo
     */
    private fun tempSenSor(child : String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(child).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                var temp : DetailGardenFirebase? = dataSnapshot.getValue(DetailGardenFirebase::class.java)
                temperature.text = temp?.value
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
     * the method to gardenInfo
     */
    private fun humSenSor(child : String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(child).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                var temp : DetailGardenFirebase? = dataSnapshot.getValue(DetailGardenFirebase::class.java)
                humidity.text = temp?.value
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
     * the method to gardenInfoRain
     */
    private fun gardenInfoRain(child: String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(child).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                var rain : DetailGardenFirebase? = dataSnapshot.getValue(DetailGardenFirebase::class.java)
                if(rain?.value!!.trim() == "1"){
                    rain_status.setImageResource(R.drawable.mua)
                    rain_text.text = "Trời đang mưa"
                }else{
                    rain_status.setImageResource(R.drawable.khongmua)
                    rain_text.text = "Trời Không mưa"
                }
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
     * the method to gardenInfoRain
     */
//    private fun pumpONOFFControl(){
//        database = FirebaseDatabase.getInstance().reference
//        var gardenId = getDataFromItentGardenId()
//        var garden = getDataFromItent()
//        var listDevice = database1.findAllDeviceByGardenId(gardenId)
//        // My top posts by number of stars
////        if(listDevice.isNullOrEmpty()){
////            for (item in  0 until listDevice.size){
////                if()
////            }
////        }
//
//        var gardenChild = getDataFromItent() + "D2"
//        database.child(garden).child(gardenChild).addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
//                var KHUVUON1 : DetailGardenFirebaseRain? = dataSnapshot.getValue(DetailGardenFirebaseRain::class.java)
//                if(KHUVUON1?.Rain?.trim().equals("1")){
//                    imageView.setImageResource(R.drawable.mua)
//                    rain_text.text = "Trời đang mưa"
//                }else{
//                    imageView.setImageResource(R.drawable.khongmua)
//                    rain_text.text = "Trời Không mưa"
//                }
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
//
//            }
//
//            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//        })
//   }

    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): String {
        val bundle: Bundle = intent.extras
        return bundle.get("garden_code") as String

    }


    /**
     * the method to get data from intent
     */
    private fun getDataFromItentGardenId(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("garden_id") as Int

    }


    private fun rainAlert(){
        this.rain_status.setImageResource(R.drawable.khongmua)
        this.rain_text.text = "Trời không mưa"
    }


}
