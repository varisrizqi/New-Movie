package com.tipiz.core.domain.model.nowplaying

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataNowPlaying(
    var id: Int = 0,
    var originalTitle: String = "",
    var posterPath: String? = "",
    var voteAverage: Double = 0.0
) : Parcelable