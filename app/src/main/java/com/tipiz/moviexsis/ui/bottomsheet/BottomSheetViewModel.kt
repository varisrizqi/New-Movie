package com.tipiz.moviexsis.ui.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tipiz.core.domain.model.detail.DataDetail
import com.tipiz.core.domain.model.trailer.DataTrailer
import com.tipiz.core.domain.usecase.MovieUseCase
import com.tipiz.core.utils.state.UiState
import com.tipiz.core.utils.state.asMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BottomSheetViewModel(private val useCase: MovieUseCase):ViewModel() {

    private val _responseDetail: MutableStateFlow<UiState<DataDetail>> =
        MutableStateFlow((UiState.Empty))
    val responseDetail = _responseDetail.asStateFlow()

    private val _responseTrailer: MutableStateFlow<UiState<DataTrailer>> =
        MutableStateFlow((UiState.Empty))
    val responseTrailer = _responseTrailer.asStateFlow()

    fun detailMovie(movieId: Int) {
        viewModelScope.launch {
            _responseDetail.asMutableStateFlow {
                useCase.fetchMovieDetails(movieId = movieId)

            }
        }
    }

    fun fetchTrailerMovie(movieId: Int) {
        viewModelScope.launch {
            _responseTrailer.asMutableStateFlow {
                useCase.fetchTrailerMovie(movieId = movieId)

            }
        }
    }

}