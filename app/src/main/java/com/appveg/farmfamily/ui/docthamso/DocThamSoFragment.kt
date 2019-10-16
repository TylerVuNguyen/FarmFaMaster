package com.appveg.farmfamily.ui.docthamso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.khuvuon.KhuVuonViewModel

class DocThamSoFragment: Fragment() {

    private lateinit var DocThamSoViewModel: KhuVuonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DocThamSoViewModel =
            ViewModelProviders.of(this).get(KhuVuonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_khuvuon, container, false)
        val textView: TextView = root.findViewById(R.id.text_docthamso)
        DocThamSoViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}