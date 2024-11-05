package com.tipiz.core.domain.model.search

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataSearch(
    var id: Int? = 0,
    var originalTitle: String? = "",
    var posterPath: String? = "",
    var voteAverage: Double? = 0.0
): Parcelable