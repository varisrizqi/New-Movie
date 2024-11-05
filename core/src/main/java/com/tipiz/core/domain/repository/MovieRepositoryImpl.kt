package com.tipiz.core.domain.repository

import com.tipiz.core.data.network.datasource.RemoteDataSource
import com.tipiz.core.data.network.response.detail.DetailResponse
import com.tipiz.core.data.network.response.nowplaying.NowPlayingResponse
import com.tipiz.core.data.network.response.popular.PopularResponse
import com.tipiz.core.data.network.response.search.SearchResponse
import com.tipiz.core.data.network.response.toprated.TopRatedResponse
import com.tipiz.core.data.network.response.trailer.TrailerResponse
import com.tipiz.core.utils.state.safeDataCall

class MovieRepositoryImpl(
    private val remote: RemoteDataSource
) : MovieRepository {
    override suspend fun fetchNowPlaying(page: Int): NowPlayingResponse {
        return safeDataCall {
            remote.fetchNowPlaying(page = page)
        }
    }

    override suspend fun fetchPopularMovies(page: Int): PopularResponse {
        return  safeDataCall {
            remote.fetchPopularMovies(page = page)
        }
    }

    override suspend fun fetchTopRatedMovies(page: Int): TopRatedResponse {
        return  safeDataCall {
            remote.fetchTopRatedMovies(page = page)
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): DetailResponse {
        return safeDataCall { remote.fetchMovieDetails(movieId = movieId) }
    }

    override suspend fun fetchTrailerMovie(movieId: Int): TrailerResponse {
        return safeDataCall { remote.fetchTrailerMovie(movieId = movieId) }
    }

    override suspend fun fetchSearch(query: String): SearchResponse {
        return safeDataCall { remote.fetchSearch(query = query) }
    }
}