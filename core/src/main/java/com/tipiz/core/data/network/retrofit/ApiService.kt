package com.tipiz.core.data.network.retrofit

import com.tipiz.core.data.network.response.detail.DetailResponse
import com.tipiz.core.data.network.response.nowplaying.NowPlayingResponse
import com.tipiz.core.data.network.response.popular.PopularResponse
import com.tipiz.core.data.network.response.search.SearchResponse
import com.tipiz.core.data.network.response.toprated.TopRatedResponse
import com.tipiz.core.data.network.response.trailer.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("page") page: Int? = null,
        @Query("region") region: String? = "ID"
    ): PopularResponse

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(
        @Query("page") page: Int? = null,
        @Query("region") region: String? = "ID"
    ): NowPlayingResponse

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("page") page: Int? = null,
        @Query("region") region: String? = "ID"
    ): TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int
    ): DetailResponse

    @GET("search/movie")
    suspend fun fetchSearch(
        @Query("query") query: String = "",
        @Query("include_adult") includeAdult: Boolean? = false,
        @Query("page") page: Int? = null,
        @Query("region") region: String? = "ID"
    ): SearchResponse

    @GET("movie/{movie_id}/videos")
    suspend fun fetchTrailerMovie(
        @Path("movie_id") movieId: Int
    ): TrailerResponse


}