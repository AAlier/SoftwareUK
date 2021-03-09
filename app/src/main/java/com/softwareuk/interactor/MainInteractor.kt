package com.softwareuk.interactor

import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.LineDataSet

class MainInteractor(
    private val assetGateway: AssetGateway
) {

    fun getCandles(filename: String): List<CandleDataSet> {
        val quotes = assetGateway.parseQuotes(filename)
        return quotes.items.map { quote ->
            CandleDataSet(quote.getData(), quote.symbol)
        }
    }

    fun getPerformance(filename: String): List<LineDataSet> {
        val quotes = assetGateway.parseQuotes(filename)
        return quotes.items.map { quote ->
            LineDataSet(quote.getData(), quote.symbol)
        }
    }
}