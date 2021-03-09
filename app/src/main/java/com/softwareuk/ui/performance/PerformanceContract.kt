package com.softwareuk.ui.performance

import androidx.annotation.StringRes
import com.github.mikephil.charting.data.LineDataSet
import com.softwareuk.common.mvp.MvpPresenter
import com.softwareuk.common.mvp.MvpView
import com.softwareuk.data.Period

interface PerformanceContract {

    interface View : MvpView {
        fun showLoading(isLoading: Boolean)
        fun showError(@StringRes textRes: Int)
        fun showQuotes(period: Period, items: List<LineDataSet>)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData(period: Period)
    }
}