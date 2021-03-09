package com.softwareuk.interactor

import android.content.res.AssetManager
import android.util.LruCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softwareuk.data.ContentResponse
import com.softwareuk.data.QuoteResponse
import kotlinx.coroutines.Dispatchers
import java.io.IOException

typealias Quotes = ContentResponse<QuoteResponse>

class AssetGateway(
    private val assetManager: AssetManager
) {
    private val cache = LruCache<String, Any>(5 * 1024)

    fun parseQuotes(filename: String): QuoteResponse {
        if (cache.get(filename) == null) {
            val json = getJsonDataFromAsset(filename)
            cache.put(filename, handleResponse(json))
        }
        return cache.get(filename) as QuoteResponse
    }

    private fun handleResponse(jsonString: String): QuoteResponse = with(Dispatchers.IO) {
        val typeToken = object : TypeToken<Quotes>() {}.type
        return@with Gson().fromJson<Quotes>(jsonString, typeToken).content
    }

    private fun getJsonDataFromAsset(fileName: String): String {
        try {
            val stream = assetManager.open(fileName)
            return stream.bufferedReader().use {
                it.readText()
            }
        } catch (e: IOException) {
            throw e
        }
    }

    fun clear() {
        cache.evictAll()
    }
}