package com.softwareuk.ui.candle

import androidx.lifecycle.viewModelScope
import com.softwareuk.R
import com.softwareuk.common.mvp.BasePresenter
import com.softwareuk.data.Period
import com.softwareuk.interactor.MainInteractor
import kotlinx.coroutines.launch

class CandlePresenter(
    private val interactor: MainInteractor
) : BasePresenter<CandleContract.View>(),
    CandleContract.Presenter {

    override fun loadData(period: Period) {
        viewModelScope.launch {
            try {
                view?.showLoading(isLoading = true)
                val response = interactor.getCandles(period.filename)
                view?.showQuotes(period, response)
            } catch (e: Exception) {
                view?.showError(R.string.error_processing)
            } finally {
                view?.showLoading(isLoading = false)
            }
        }
    }
}