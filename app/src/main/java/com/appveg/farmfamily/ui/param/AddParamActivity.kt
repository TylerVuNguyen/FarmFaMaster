package com.appveg.farmfamily.ui.param

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appveg.farmfamily.R
import kotlinx.android.synthetic.main.activity_add_device.*
import kotlinx.android.synthetic.main.activity_add_param.*

class AddParamActivity : AppCompatActivity() {
    var activity = this@AddParamActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_param)

        // event action button
        actionButton()
    }


    private fun actionButton() {
        /*event add veg*/
        btn_add_param.setOnClickListener {
        }

        /*event cancel*/
        cancel_action_param.setOnClickListener {
            activity.finish()
        }


    }
}
