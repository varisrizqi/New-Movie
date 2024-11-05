package com.tipiz.core.domain.usecase

import com.tipiz.core.domain.model.detail.DataDetail
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.core.domain.model.trailer.DataTrailer
import com.tipiz.core.domain.repository.MovieRepository
import com.tipiz.core.utils.DataMapper.toUiData
import com.tipiz.core.utils.DataMapper.toUiListData
import com.tipiz.core.utils.state.safeDataCall

class MovieInteractor(
    private val repo: MovieRepository
) : MovieUseCase {
    override suspend fun fetchNowPlaying(page: Int): List<DataNowPlaying> = safeDataCall {
        repo.fetchNowPlaying(page = page).toUiListData()
    }


    override suspend fun fetchPopularMovies(page: Int): List<DataPopular> = safeDataCall {
        repo.fetchPopularMovies(page = page).toUiListData()
    }

    override suspend fun fetchMovieDetails(movieId: Int): DataDetail = safeDataCall {
        repo.fetchMovieDetails(movieId = movieId).toUiData()
    }

    override suspend fun fetchSearch(query: String): List<DataSearch> = safeDataCall {
        repo.fetchSearch(query = query).toUiData()
    }

    override suspend fun fetchTrailerMovie(movieId: Int): DataTrailer = safeDataCall {
        repo.fetchTrailerMovie(movieId = movieId).toUiData()
    }

    override suspend fun fetchTopRatedMovies(page: Int): List<DataTopRated> = safeDataCall {
        repo.fetchTopRatedMovies(page = page).toUiListData()
    }
}