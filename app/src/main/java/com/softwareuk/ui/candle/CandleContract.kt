package com.softwareuk.ui.candle

import androidx.annotation.StringRes
import com.github.mikephil.charting.data.CandleDataSet
import com.softwareuk.common.mvp.MvpPresenter
import com.softwareuk.common.mvp.MvpView
import com.softwareuk.data.Period

interface CandleContract {

    interface View : MvpView {
        fun showLoading(isLoading: Boolean)
        fun showError(@StringRes textRes: Int)
        fun showQuotes(period: Period, items: List<CandleDataSet>)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData(period: Period)
    }
}