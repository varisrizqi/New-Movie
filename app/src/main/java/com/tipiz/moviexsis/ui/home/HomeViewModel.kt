package com.tipiz.moviexsis.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.core.domain.usecase.MovieUseCase
import com.tipiz.core.utils.state.UiState
import com.tipiz.core.utils.state.asMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: MovieUseCase): ViewModel() {


    private val _responsePopular: MutableStateFlow<UiState<List<DataPopular>>> =
        MutableStateFlow((UiState.Empty))
    val responsePopular = _responsePopular.asStateFlow()

    private val _responseTopRated: MutableStateFlow<UiState<List<DataTopRated>>> =
        MutableStateFlow((UiState.Empty))
    val responseTopRated = _responseTopRated.asStateFlow()

    private val _responseNowPlaying: MutableStateFlow<UiState<List<DataNowPlaying>>> =
        MutableStateFlow((UiState.Empty))
    val responseNowPlaying = _responseNowPlaying.asStateFlow()


    fun showPopular(page: Int) {
        viewModelScope.launch {
            _responsePopular.asMutableStateFlow {
                useCase.fetchPopularMovies(page = page)
            }
        }
    }

    fun showTopRated(page:Int){
        viewModelScope.launch {
            _responseTopRated.asMutableStateFlow {
                useCase.fetchTopRatedMovies(page=page)
            }
        }
    }

    fun showNowPlaying(page:Int){
        viewModelScope.launch {
            _responseNowPlaying.asMutableStateFlow {
                useCase.fetchNowPlaying(page=page)
            }
        }
    }

}