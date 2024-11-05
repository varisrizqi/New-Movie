package com.tipiz.core.data.network.datasource

import com.tipiz.core.data.network.response.detail.DetailResponse
import com.tipiz.core.data.network.response.nowplaying.NowPlayingResponse
import com.tipiz.core.data.network.response.popular.PopularResponse
import com.tipiz.core.data.network.response.search.SearchResponse
import com.tipiz.core.data.network.response.toprated.TopRatedResponse
import com.tipiz.core.data.network.response.trailer.TrailerResponse
import com.tipiz.core.data.network.retrofit.ApiService
import com.tipiz.core.utils.state.safeApiCall

class RemoteDataSource(private val endpoint: ApiService) {

    suspend fun fetchPopularMovies(page: Int): PopularResponse {
        return safeApiCall {
            endpoint.fetchPopularMovies(page = page)
        }
    }

    suspend fun fetchNowPlaying(page: Int): NowPlayingResponse {
        return safeApiCall {
            endpoint.fetchNowPlayingMovies(page = page)
        }
    }

    suspend fun fetchSearch(query: String): SearchResponse {
        return safeApiCall {
            endpoint.fetchSearch(query = query)
        }
    }

    suspend fun fetchMovieDetails(movieId: Int): DetailResponse {
        return safeApiCall {
            endpoint.fetchMovieDetails(movieId = movieId)
        }
    }

    suspend fun fetchTopRatedMovies(page: Int): TopRatedResponse {
        return safeApiCall {
            endpoint.fetchTopRatedMovies(page = page)
        }
    }

    suspend fun fetchTrailerMovie(movieId: Int): TrailerResponse {
        return safeApiCall {
            endpoint.fetchTrailerMovie(movieId = movieId)
        }
    }

}