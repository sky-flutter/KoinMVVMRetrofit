package com.example.koinmvvmretrofit.di

import com.example.koinmvvmretrofit.ui.ListPostViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListPostViewModel(get()) }
}