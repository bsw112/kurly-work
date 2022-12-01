package com.manta.kurly_work.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.manta.kurly_work.R
import com.manta.kurly_work.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagingAdapter = MainPagingAdapter()
        binding.lifecycleOwner = this
        binding.rvSection.adapter = pagingAdapter
        binding.vm = vm

        lifecycleScope.launch {
            vm.sectionUiModelList.collect {
                pagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collect {
                val refresh = it.refresh
                val append = it.append
                if (refresh is LoadState.Error) {
                    Log.e("kurly_debug", refresh.error.message, refresh.error)
                }
                if (append is LoadState.Error) {
                    Log.e("kurly_debug", append.error.message, append.error)
                }
            }
        }
    }
}


fun CombinedLoadStates.isError() = refresh is LoadState.Error || append is LoadState.Error