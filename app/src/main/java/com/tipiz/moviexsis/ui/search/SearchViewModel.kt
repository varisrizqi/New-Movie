package com.tipiz.moviexsis.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.core.domain.usecase.MovieUseCase
import com.tipiz.core.utils.state.UiState
import com.tipiz.core.utils.state.asMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: MovieUseCase): ViewModel() {

    private val _responseSearch: MutableStateFlow<UiState<List<DataSearch>>> =
        MutableStateFlow((UiState.Empty))
    val responseSearch = _responseSearch.asStateFlow()

    fun showSearch(query:String){
        viewModelScope.launch {
            _responseSearch.asMutableStateFlow {
                useCase.fetchSearch(query=query)
            }
        }
    }

}