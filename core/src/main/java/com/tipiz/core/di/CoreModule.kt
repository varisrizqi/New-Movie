package com.tipiz.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.tipiz.core.data.network.retrofit.ApiService
import com.tipiz.core.data.network.retrofit.NetworkClient
import com.tipiz.core.data.network.datasource.RemoteDataSource
import com.tipiz.core.domain.repository.MovieRepository
import com.tipiz.core.domain.repository.MovieRepositoryImpl
import com.tipiz.core.domain.usecase.MovieInteractor
import com.tipiz.core.domain.usecase.MovieUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object CoreModule {

    private val dataSourceModule = module {
        single { RemoteDataSource(get()) }
    }

    private val repositoryModule = module {
        single<MovieRepository> { MovieRepositoryImpl(get()) }
    }

    private val useCase = module {
        single<MovieUseCase> { MovieInteractor(get()) }
    }
    private val networkModules = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get()) }
        single<ApiService> { get<NetworkClient>().create() }
    }

    val modules: List<Module> = listOf(
        dataSourceModule,
        repositoryModule,
        useCase,
        networkModules,

    )

}