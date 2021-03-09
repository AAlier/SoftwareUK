package com.softwareuk.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.softwareuk.R
import com.softwareuk.ui.candle.CandleFragment
import com.softwareuk.ui.performance.PerformanceFragment
import com.softwareuk.ui.replaceFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartTextView.setOnClickListener {
            replaceFragment(CandleFragment())
        }
        performanceTextView.setOnClickListener {
            replaceFragment(PerformanceFragment())
        }
    }
}