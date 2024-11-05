package com.tipiz.core.domain.model.popular

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPopular(
    var id: Int = 0,
    var originalTitle: String = "",
    var posterPath: String? = "",
    var voteAverage: Double = 0.0,
    var overview: String? = ""
) : Parcelable