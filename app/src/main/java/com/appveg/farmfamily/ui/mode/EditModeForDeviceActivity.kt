package com.appveg.farmfamily.ui.mode

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_add_mode_for_device.*
import kotlinx.android.synthetic.main.activity_add_mode_for_device.time_picker_24
import kotlinx.android.synthetic.main.activity_edit_mode_for_device.*
import java.util.*

class EditModeForDeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mode_for_device)

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
    }
}
