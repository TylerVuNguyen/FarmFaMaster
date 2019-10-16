package com.appveg.farmfamily.ui.thietbi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R

class ThietBiFragment : Fragment() {

    private lateinit var thietBiViewModel: ThietBiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thietBiViewModel =
            ViewModelProviders.of(this).get(ThietBiViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_thietbi, container, false)
        val textView: TextView = root.findViewById(R.id.text_thietbi)
        thietBiViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}