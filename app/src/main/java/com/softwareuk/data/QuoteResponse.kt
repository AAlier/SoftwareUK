package com.softwareuk.data

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("quoteSymbols")
    val items: List<Quote>
)