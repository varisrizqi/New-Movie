package com.tipiz.core.data.network.response.detail

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String = "",

    @field:SerializedName("imdb_id")
    val imdbId: String = "",

    @field:SerializedName("video")
    val video: Boolean = false,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String = "",

    @field:SerializedName("revenue")
    val revenue: Int = 0,

    @field:SerializedName("genres")
    val genres: List<GenresItem> = listOf(),

    @field:SerializedName("popularity")
    val popularity: Double = 0.0,

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem> = listOf(),

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("vote_count")
    val voteCount: Int = 0,

    @field:SerializedName("budget")
    val budget: Int = 0,

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("original_title")
    val originalTitle: String = "",

    @field:SerializedName("runtime")
    val runtime: Int = 0,

    @field:SerializedName("poster_path")
    val posterPath: String = "",

    @field:SerializedName("origin_country")
    val originCountry: List<String> = listOf(),

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem> = listOf(),

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem> = listOf(),

    @field:SerializedName("release_date")
    val releaseDate: String = "",

    @field:SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @field:SerializedName("belongs_to_collection")
    val belongsToCollection: DataBelongsToCollection = DataBelongsToCollection(),

    @field:SerializedName("tagline")
    val tagline: String = "",

    @field:SerializedName("adult")
    val adult: Boolean = false,

    @field:SerializedName("homepage")
    val homepage: String = "",

    @field:SerializedName("status")
    val status: String = ""
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(

    @field:SerializedName("logo_path")
    val logoPath: String = "",

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("origin_country")
    val originCountry: String = ""
) : Parcelable

@Parcelize
data class GenresItem(

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("id")
    val id: Int = 0
) : Parcelable

@Parcelize
data class SpokenLanguagesItem(

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("iso_639_1")
    val iso6391: String = "",

    @field:SerializedName("english_name")
    val englishName: String = ""
) : Parcelable

@Parcelize
data class ProductionCountriesItem(

    @field:SerializedName("iso_3166_1")
    val iso31661: String = "",

    @field:SerializedName("name")
    val name: String = ""
) : Parcelable

@Parcelize
data class DataBelongsToCollection(

    @field:SerializedName("backdrop_path")
    val backdropPath: String = "",

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("poster_path")
    val posterPath: String = ""
) : Parcelable
