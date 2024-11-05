package com.tipiz.core.domain.model.toprated

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTopRated(
    var id: Int = 0,
    var originalTitle: String = "",
    var posterPath: String? = "",
    var voteAverage: Double = 0.0
) : Parcelable