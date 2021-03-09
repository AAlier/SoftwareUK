package com.softwareuk.data

import com.github.mikephil.charting.data.CandleEntry

data class Quote(
    val symbol: String,
    val timestamps: List<Long>,
    val opens: List<Double>,
    val closures: List<Double>,
    val highs: List<Double>,
    val lows: List<Double>,
    val volumes: List<Int>
) {

    fun getData(): List<CandleEntry> {
        val items = mutableListOf<CandleEntry>()
        timestamps.withIndex().forEach { (index, value) ->
            val item = CandleEntry(
                index.toFloat(),
                highs[index].toFloat(),
                lows[index].toFloat(),
                opens[index].toFloat(),
                closures[index].toFloat()
            )
            items.add(item)
        }
        return items
    }
}