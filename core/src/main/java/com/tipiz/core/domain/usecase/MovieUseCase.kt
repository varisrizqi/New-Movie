package com.tipiz.core.domain.usecase

import com.tipiz.core.domain.model.detail.DataDetail
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.core.domain.model.trailer.DataTrailer

interface MovieUseCase {

    suspend fun fetchPopularMovies(page: Int): List<DataPopular>
    suspend fun fetchNowPlaying(page: Int): List<DataNowPlaying>
    suspend fun fetchTopRatedMovies(page: Int): List<DataTopRated>
    suspend fun fetchMovieDetails(movieId: Int): DataDetail
    suspend fun fetchTrailerMovie(movieId: Int): DataTrailer
    suspend fun fetchSearch(query: String): List<DataSearch>

}