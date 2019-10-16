package com.appveg.farmfamily.ui.vuonrau

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R

class VuonRauFragment : Fragment() {

    private lateinit var vuonRauViewModel: VuonRauViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vuonRauViewModel =
            ViewModelProviders.of(this).get(VuonRauViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vuonrau, container, false)
        val textView: TextView = root.findViewById(R.id.text_vuonrau)
        vuonRauViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}