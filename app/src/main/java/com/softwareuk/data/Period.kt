package com.softwareuk.data

import androidx.annotation.StringRes
import com.softwareuk.R

enum class Period(@StringRes val titleRes: Int, val filename: String) {
    MONTHLY(R.string.month, "month.json"),
    WEEKLY(R.string.week, "week.json")
}