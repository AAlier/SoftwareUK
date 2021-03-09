package com.softwareuk.ui.performance

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.snackbar.Snackbar
import com.softwareuk.R
import com.softwareuk.common.mvp.BaseMvpFragment
import com.softwareuk.data.Period
import com.softwareuk.ui.performance.dialog.SymbolPickerDialog
import kotlinx.android.synthetic.main.fragment_performance.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PerformanceFragment :
    BaseMvpFragment<PerformanceContract.View, PerformanceContract.Presenter>(R.layout.fragment_performance),
    PerformanceContract.View {
    override val presenter by viewModel<PerformancePresenter>()

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

    override fun showQuotes(period: Period, items: List<LineDataSet>) {
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

    private fun setChartData(period: Period, data: LineDataSet) {
        toolbar.title = "${getString(period.titleRes)} ${data.label}"
        chart.resetTracking()
        chart.data = LineData(data.applyUiUpdates())
        chart.invalidate()
    }
}

private fun LineDataSet.applyUiUpdates(): LineDataSet {
    color = Color.BLACK
    lineWidth = 0.5f
    setDrawValues(false)
    setDrawCircles(false)
    mode = LineDataSet.Mode.LINEAR
    setDrawFilled(false)
    return this
}