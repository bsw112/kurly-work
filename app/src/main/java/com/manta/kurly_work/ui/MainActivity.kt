package com.manta.kurly_work.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.manta.kurly_work.R
import com.manta.kurly_work.databinding.ActivityMainBinding
import com.manta.kurly_work.isEndOfPageReached
import com.manta.kurly_work.isLoading
import com.manta.kurly_work.onError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pagingAdapter = MainPagingAdapter()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.rvSection.adapter = pagingAdapter
        binding.rvSection.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.vm = vm

        binding.layoutRefresh.setOnRefreshListener {
            pagingAdapter.refresh()
        }

        lifecycleScope.launch {
            vm.sectionUiModelList.collect {
                pagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collect {
                binding.layoutRefresh.isRefreshing = it.isLoading()

                it.onError { t ->
                    Log.e("kurly_debug", t.message, t)
                }
            }
        }
    }
}