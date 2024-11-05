package com.tipiz.core.utils

import com.tipiz.core.data.network.response.detail.DetailResponse
import com.tipiz.core.data.network.response.detail.GenresItem
import com.tipiz.core.data.network.response.nowplaying.NowPlayingItem
import com.tipiz.core.data.network.response.nowplaying.NowPlayingResponse
import com.tipiz.core.data.network.response.popular.PopularItem
import com.tipiz.core.data.network.response.popular.PopularResponse
import com.tipiz.core.data.network.response.search.SearchItem
import com.tipiz.core.data.network.response.search.SearchResponse
import com.tipiz.core.data.network.response.toprated.TopRatedItem
import com.tipiz.core.data.network.response.toprated.TopRatedResponse
import com.tipiz.core.data.network.response.trailer.TrailerItem
import com.tipiz.core.data.network.response.trailer.TrailerResponse
import com.tipiz.core.domain.model.detail.DataDetail
import com.tipiz.core.domain.model.detail.DetailGenresItem
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.core.domain.model.trailer.DataTrailer
import com.tipiz.core.domain.model.trailer.TraileResultItem

object DataMapper {

    //popular
    fun PopularResponse.toUiListData() = results.map { it.toUiData() }

    private fun PopularItem.toUiData() = DataPopular(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteAverage = voteAverage,
        overview = overview
    )

    //now_playing
    fun NowPlayingResponse.toUiListData() =
        results.map { nowPlayingItem -> nowPlayingItem.toUiData() }.toList()

    private fun NowPlayingItem.toUiData() = DataNowPlaying(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteAverage = voteAverage
    )

    //detail
    fun DetailResponse.toUiData() = DataDetail(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        tagline = tagline,
        genres = genres.map { genresItem -> genresItem.toGenreItem() }
    )

    private fun GenresItem.toGenreItem() = DetailGenresItem(
        name = name,
        id = id
    )

    //search
    fun SearchResponse.toUiData() = results.map { item -> item.toSearchItem() }
    private fun SearchItem.toSearchItem() = DataSearch(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteAverage = voteAverage
    )

    //top_rated
    fun TopRatedResponse.toUiListData() = results.map { it.toUiData() }.toList()

    private fun TopRatedItem.toUiData() = DataTopRated(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteAverage = voteAverage
    )

    //trailer
    fun TrailerResponse.toUiData() = DataTrailer(
        id = id,
        results = results.map { it.toTrailerItem() }
    )

    private fun TrailerItem.toTrailerItem() = TraileResultItem(
        key = key

    )

}