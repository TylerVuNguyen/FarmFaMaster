package com.appveg.farmfamily.ui.khuvuon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.appveg.farmfamily.R

class KhuVuonFragment : Fragment() {

    private lateinit var khuVuonViewModel: KhuVuonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        khuVuonViewModel =
            ViewModelProviders.of(this).get(KhuVuonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_khuvuon, container, false)
        val textView: TextView = root.findViewById(R.id.text_khuvuon)
        khuVuonViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}