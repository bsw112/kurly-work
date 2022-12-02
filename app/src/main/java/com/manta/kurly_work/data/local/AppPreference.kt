package com.manta.kurly_work.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreference @Inject constructor(
    private val preferences: SharedPreferences
) {

    var favoriteProductIdList: Set<String>
        get() = preferences.getStringSet(PREF_FAVORITE_PRODUCT_ID_LIST, emptySet()) ?: emptySet()
        set(value) = preferences.edit().putStringSet(PREF_FAVORITE_PRODUCT_ID_LIST, value).apply()

    fun addFavoriteProduct(productId: Int) {
        favoriteProductIdList = favoriteProductIdList + productId.toString()

    }

    fun removeFavoriteProduct(productId: Int) {
        favoriteProductIdList = favoriteProductIdList - productId.toString()
    }

    companion object {
        const val PREF_FAVORITE_PRODUCT_ID_LIST = "PREF_FAVORITE_PRODUCT_ID_LIST"
    }
}