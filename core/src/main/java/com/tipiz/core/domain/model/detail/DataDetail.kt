package com.tipiz.core.domain.model.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDetail(
    var id: Int = 0,
    var originalTitle: String = "",
    var overview: String = "",
    var posterPath: String? = "",
    var backdropPath: String? = "",
    var voteAverage: Double = 0.0,
    var tagline: String = "",
    var genres: List<DetailGenresItem> = listOf()
) : Parcelable

@Parcelize
data class DetailGenresItem(
    var name: String = "",
    var id: Int = 0
) : Parcelable