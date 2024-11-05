package com.tipiz.moviexsis.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.tipiz.moviexsis.ui.home.HomeViewModel
import com.tipiz.moviexsis.ui.bottomsheet.BottomSheetViewModel
import com.tipiz.moviexsis.ui.search.SearchViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::BottomSheetViewModel)
        viewModelOf(::SearchViewModel)
    }

    val modules: List<Module> = listOf(
        viewModelModule,
    )

}