package com.example.koinmvvmretrofit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvmretrofit.data.model.Repo
import com.example.koinmvvmretrofit.BR
import com.example.koinmvvmretrofit.R
import com.example.koinmvvmretrofit.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ListPostActivity : AppCompatActivity() {
    private lateinit var adapter: ListPostAdapter
    private val listPostViewModel: ListPostViewModel by viewModel()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        adapter = ListPostAdapter()
        binding.rvList.adapter = adapter
        listPostViewModel.fetchRepos()
        observeData()
    }

    private fun observeData() {
        listPostViewModel.getRepos().observe(this, Observer<List<Repo>> {
            if (it != null) {
                binding.setVariable(BR.isError, false)
                binding.setVariable(BR.isLoading, false)
                adapter.addPostData(it)
            }
        })


        listPostViewModel.getLoading().observe(this, Observer<Boolean> {
            if (it != null) {
                binding.setVariable(BR.isError, false)
                binding.setVariable(BR.isLoading, true)
            }
        })

        listPostViewModel.getError().observe(this, Observer<Boolean> {
            if (it != null) {
                binding.setVariable(BR.isError, it)
                binding.setVariable(BR.isLoading, false)
            }
        })
    }


}
