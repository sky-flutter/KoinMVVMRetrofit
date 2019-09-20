package com.example.koinmvvmretrofit.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvmretrofit.data.model.Repo
import com.example.koinmvvmretrofit.data.rest.RepoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListPostViewModel(private val repoService: RepoService) : ViewModel() {
    private var compositeDisposable: CompositeDisposable? = null
    var repos = MutableLiveData<List<Repo>>()
    var repoLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()


    init {
        compositeDisposable = CompositeDisposable()
//        fetchRepos()
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError
    }

     fun fetchRepos() {
        loading.value = true
        val repoDisposable = repoService.getRepositories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                DisposableSingleObserver<List<Repo>>() {
                override fun onSuccess(t: List<Repo>) {
                    repos.value = t
                    loading.value = false
                    repoLoadError.value = false
                }

                override fun onError(e: Throwable) {
                    loading.value = false
                    e.printStackTrace()
                    repoLoadError.value = true
                }
            })
        compositeDisposable?.add(repoDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
        compositeDisposable = null
    }


}