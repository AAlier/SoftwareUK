package com.softwareuk.ui.candle

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.google.android.material.snackbar.Snackbar
import com.softwareuk.R
import com.softwareuk.common.mvp.BaseMvpFragment
import com.softwareuk.data.Period
import com.softwareuk.ui.candle.dialog.SymbolPickerDialog
import kotlinx.android.synthetic.main.fragment_candle.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CandleFragment :
    BaseMvpFragment<CandleContract.View, CandleContract.Presenter>(R.layout.fragment_candle),
    CandleContract.View {
    override val presenter by viewModel<CandlePresenter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        toolbar.inflateMenu(R.menu.period)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.month -> {
                    presenter.loadData(Period.MONTHLY)
                    return@setOnMenuItemClickListener true
                }
                R.id.week -> {
                    presenter.loadData(Period.WEEKLY)
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }

        presenter.loadData(Period.MONTHLY)
    }

    override fun showLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }

    override fun showError(textRes: Int) {
        Snackbar.make(rootView, textRes, Snackbar.LENGTH_SHORT).show()
    }

    override fun showQuotes(period: Period, items: List<CandleDataSet>) {
        fab.isVisible = items.isNotEmpty()
        fab.setOnClickListener {
            SymbolPickerDialog.show(
                fragment = this,
                title = getString(period.titleRes),
                actions = items
            ) { data ->
                setChartData(period, data)
            }
        }
        items.takeIf { it.isNotEmpty() }?.apply { setChartData(period, first()) }
    }

    private fun setChartData(period: Period, data: CandleDataSet) {
        toolbar.title = "${getString(period.titleRes)} ${data.label}"
        chart.resetTracking()
        chart.data = CandleData(data.applyUiUpdates())
        chart.invalidate()
    }
}

private fun CandleDataSet.applyUiUpdates(): CandleDataSet {
    setDrawIcons(false)
    axisDependency = YAxis.AxisDependency.LEFT
    shadowColor = Color.DKGRAY
    shadowWidth = 0.7f
    decreasingColor = Color.RED
    decreasingPaintStyle = Paint.Style.FILL
    increasingColor = Color.rgb(122, 242, 84)
    increasingPaintStyle = Paint.Style.STROKE
    neutralColor = Color.BLUE
    return this
}