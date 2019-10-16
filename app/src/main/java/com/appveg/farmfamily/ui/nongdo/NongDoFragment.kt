package com.appveg.farmfamily.ui.nongdo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R

class NongDoFragment : Fragment() {

    private lateinit var nongDoViewModel: NongDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nongDoViewModel =
            ViewModelProviders.of(this).get(NongDoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nongdo, container, false)
        val textView: TextView = root.findViewById(R.id.text_nongdo)
        nongDoViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}