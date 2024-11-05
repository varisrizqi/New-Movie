package com.tipiz.core.domain.model.trailer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTrailer(
    var id: Int = 0,
    var results: List<TraileResultItem> = listOf()
) : Parcelable

@Parcelize
data class TraileResultItem(
    var key: String = ""
) : Parcelable