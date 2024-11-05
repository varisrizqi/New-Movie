package com.tipiz.core.domain.repository

import com.tipiz.core.data.network.response.detail.DetailResponse
import com.tipiz.core.data.network.response.nowplaying.NowPlayingResponse
import com.tipiz.core.data.network.response.popular.PopularResponse
import com.tipiz.core.data.network.response.search.SearchResponse
import com.tipiz.core.data.network.response.toprated.TopRatedResponse
import com.tipiz.core.data.network.response.trailer.TrailerResponse

interface MovieRepository {

    suspend fun fetchPopularMovies(page: Int): PopularResponse
    suspend fun fetchNowPlaying(page: Int): NowPlayingResponse
    suspend fun fetchTopRatedMovies(page: Int): TopRatedResponse
    suspend fun fetchMovieDetails(movieId: Int): DetailResponse
    suspend fun fetchTrailerMovie(movieId: Int): TrailerResponse
    suspend fun fetchSearch(query: String): SearchResponse
}