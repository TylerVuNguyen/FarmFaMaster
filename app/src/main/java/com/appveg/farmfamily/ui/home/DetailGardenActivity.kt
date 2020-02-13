package com.appveg.farmfamily.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.chart.ChartActivity
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.DeviceDetail
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_garden.*
import kotlin.collections.ArrayList


class DetailGardenActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database1: Database

    var devices: ArrayList<DeviceDetail> = ArrayList()
    private lateinit var child : String
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
        var check : Int = 1
        var gardenCode = getDataFromItent()
        var database = FirebaseDatabase.getInstance().reference
        button_bat_che_mua.setOnClickListener {
            if(check == 1){
                button_bat_che_mua.background = getDrawable(R.drawable.back_on)
                button_bat_che_mua.text = getString(R.string.ON_vi)
                button_bat_che_mua.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_cai_o_mua,0,0)
                //database.child(gardenCode).child(child).child("value").setValue("ON")
                check = 0
            }else{
                button_bat_che_mua.background = getDrawable(R.drawable.back)
                button_bat_che_mua.text = getString(R.string.OFF_vi)
                button_bat_che_mua.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_cai_o_0_mua,0,0)
                //database.child(gardenCode).child(child).child("value").setValue("OFF")
                check = 1
            }
        }
        var check1 : Int = 1
        button_bom_dung_dich.setOnClickListener {
            if(check1 == 1){
                button_bom_dung_dich.background = getDrawable(R.drawable.back_on)
                button_bom_dung_dich.text = getString(R.string.ON_vi)
                button_bom_dung_dich.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom,0,0)
                database.child(gardenCode).child(child).child("value").setValue("ON")
                check1 = 0
            }else{
                button_bom_dung_dich.background = getDrawable(R.drawable.back)
                button_bom_dung_dich.text = getString(R.string.OFF_vi)
                button_bom_dung_dich.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom_0,0,0)
                database.child(gardenCode).child(child).child("value").setValue("OFF")
                check1 = 1
            }
        }
        var check2 : Int = 1
        button_bom_phun_suong.setOnClickListener {
            if(check2 == 1){
                button_bom_phun_suong.background = getDrawable(R.drawable.back_on)
                button_bom_phun_suong.text = getString(R.string.ON_vi)
                button_bom_phun_suong.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom,0,0)
                check2 = 0
            }else{
                button_bom_phun_suong.background = getDrawable(R.drawable.back)
                button_bom_phun_suong.text = getString(R.string.OFF_vi)
                button_bom_phun_suong.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom_0,0,0)
                check2 = 1
            }
        }
        var check3 : Int = 1
        button_bong_den.setOnClickListener {
            if(check3 == 1){
                button_bong_den.background = getDrawable(R.drawable.back_on)
                button_bong_den.text = getString(R.string.ON_vi)
                button_bong_den.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_bong_den_sang,0,0)
                check3 = 0
            }else{
                button_bong_den.background = getDrawable(R.drawable.back)
                button_bong_den.text = getString(R.string.OFF_vi)
                button_bong_den.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_bong_den,0,0)
                check3 = 1
            }
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
                when {
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENNHIETDO",false) -> {
                        tempSenSor(devices[item].deviceDetailCodeSS!!)
                        setting_temp.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENDOAM",false) -> {
                        humSenSor(devices[item].deviceDetailCodeSS!!)
                        setting_hum.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENMUA",false) -> {
                        gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_rain.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENPH",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_ph.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENTDS",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_tds.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("CAMBIENSIEUAM",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_level_tds.background = getDrawable(R.color.background_home)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("BATCHEMUA",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_button_bat.text = getString(R.string.da_cai_dat_vi)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("MAYBOMDUNGDICH",false) -> {
                        pumpONOFFControl(devices[item].deviceDetailCodeSS!!)
                        setting_button_dd.text = getString(R.string.da_cai_dat_vi)
                        child = devices[item].deviceDetailCodeSS!!
                    }
                    devices[item].deviceDetailCodeSS!!.contains("BONGDEN",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_button_lamp.text = getString(R.string.da_cai_dat_vi)
                    }
                    devices[item].deviceDetailCodeSS!!.contains("MAYBOMPHUNSUONG",false) -> {
                        //gardenInfoRain(devices[item].deviceDetailCodeSS!!)
                        setting_button_phun_suong.text = getString(R.string.da_cai_dat_vi)
                    }
                }

            }
        }

    }


    /**
     * the method to gardenInfo
     */
    private fun tempSenSor(deviceCode : String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(deviceCode).addChildEventListener(object : ChildEventListener {
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
    private fun humSenSor(deviceCode : String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(deviceCode).addChildEventListener(object : ChildEventListener {
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
    private fun gardenInfoRain(deviceCode: String){
        database = FirebaseDatabase.getInstance().reference
        // My top posts by number of stars
        var garden = getDataFromItent()

        database.child(garden).child(deviceCode).addChildEventListener(object : ChildEventListener {
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
    private fun pumpONOFFControl(deviceCode : String){
        database = FirebaseDatabase.getInstance().reference
        var gardenCode = getDataFromItent()
        var childValue = "value"
        database.child(gardenCode).child(deviceCode).child(childValue).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value.toString()
                if(value.trim() == "ON"){
                    button_bom_dung_dich.background = getDrawable(R.drawable.back_on)
                    button_bom_dung_dich.text = getString(R.string.ON_vi)
                    button_bom_dung_dich.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom,0,0)
                }else{
                    button_bom_dung_dich.background = getDrawable(R.drawable.back)
                    button_bom_dung_dich.text = getString(R.string.OFF_vi)
                    button_bom_dung_dich.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_may_bom_0,0,0)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("AAA", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

   }


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
