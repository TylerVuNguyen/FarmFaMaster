package com.appveg.farmfamily.ui.mode

import android.app.TimePickerDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.database.Database
import com.appveg.farmfamily.ui.device.Device
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_mode_for_device.*
import kotlinx.android.synthetic.main.activity_add_mode_for_device.time_picker_24
import kotlinx.android.synthetic.main.activity_edit_device.*
import kotlinx.android.synthetic.main.activity_edit_mode_for_device.*
import java.io.File
import java.util.*

class EditModeForDeviceActivity : AppCompatActivity() {
    private lateinit var database: Database
    var activity = this@EditModeForDeviceActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_mode_for_device)

        //time_picker_24.setIs24HourView(true)
        time_picker_24_edit.isEnabled = false

        time_mode_select_start_edit.setOnClickListener {
            var calendar = Calendar.getInstance()
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minute = calendar.get(Calendar.MINUTE)
            var timePickerDialog = TimePickerDialog(this@EditModeForDeviceActivity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    if(minute < 10){
                        var minute = "0$minute"
                        time_mode_start_edit.setText("$hour:$minute")
                    }else{
                        time_mode_start_edit.setText("$hour:$minute")
                    }

                }, hour, minute, true
            )
            timePickerDialog.show()

        }
        time_mode_select_end_edit.setOnClickListener {
            var calendar = Calendar.getInstance()
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minute = calendar.get(Calendar.MINUTE)
            var timePickerDialog = TimePickerDialog(this@EditModeForDeviceActivity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    if(minute < 10){
                        var minute = "0$minute"
                        time_mode_end_edit.setText("$hour:$minute")
                    }else{
                        time_mode_end_edit.setText("$hour:$minute")
                    }
                }, hour, minute, true
            )

            timePickerDialog.show()

        }
        initDataEdit()
    }


    private fun initDataEdit() {
        database = Database(activity)
        val modeId: Int = getDataFromItent()

        // gán lại id để tý update data
        var mode: Mode = database.findAllModeByModeId(modeId)

        this.checkbox_mode_edit.isChecked = getRepeat(mode.repeat!!)
        this.date_repeat_display_edit.text = mode.timeRepeat
        this.time_mode_start_edit.setText(mode.timeOn)
        this.time_mode_start_edit.setSelection(time_mode_start_edit.text.length)
        this.time_mode_end_edit.setText(mode.timeOff)
        this.time_mode_end_edit.setSelection(time_mode_end_edit.text.length)
        this.time_mode_on_edit.setText(mode.on)
        this.time_mode_on_edit.setSelection(time_mode_on_edit.text.length)
        this.time_mode_off_edit.setText(mode.off)
        this.time_mode_off_edit.setSelection(time_mode_off_edit.text.length)
    }


    /**
     * the method to get data from intent
     */
    private fun getDataFromItent(): Int {
        val bundle: Bundle = intent.extras
        return bundle.get("mode_id") as Int
    }

    /**
     * This method is check repeat
     */
    private fun getRepeat(checked: String): Boolean {
        if (checked == "1") {
            return true
        }
        return false
    }

}
