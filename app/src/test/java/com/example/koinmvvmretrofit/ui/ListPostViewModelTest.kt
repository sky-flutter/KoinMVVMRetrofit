package com.example.koinmvvmretrofit.ui

import com.example.koinmvvmretrofit.data.rest.RepoService
import com.example.koinmvvmretrofit.di.apiModule
import com.example.koinmvvmretrofit.di.appModule
import com.example.koinmvvmretrofit.di.networkModule
import com.example.koinmvvmretrofit.di.viewModelModule
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ListPostViewModelTest : KoinTest {
    private val repoService: RepoService by inject()
    lateinit var mListPostViewModel: ListPostViewModel
    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidLogger()
            modules(listOf(appModule, networkModule, apiModule, viewModelModule))
        }

        mListPostViewModel = ListPostViewModel(repoService)

    }

    @Test
    fun fetchReposTest() {
        val mockitoWebServer = MockWebServer()
        mockitoWebServer.start()
        val httpUrl = mockitoWebServer.url("https://api.github.com/orgs/Google/repos")
        

    }

}