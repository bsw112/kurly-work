package com.manta.kurly_work.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.manta.kurly_work.R
import com.manta.kurly_work.TAG
import com.manta.kurly_work.data.local.AppPreference
import com.manta.kurly_work.databinding.ActivityMainBinding
import com.manta.kurly_work.isLoading
import com.manta.kurly_work.onError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    @Inject
    lateinit var preference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagingAdapter = MainPagingAdapter(onClickFavorite = { productId, isSelected ->
            if (isSelected) {
                preference.addFavoriteProduct(productId)
            } else {
                preference.removeFavoriteProduct(productId)
            }
        })

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
            vm.sectionUiModelList.flowWithLifecycle(lifecycle).collectLatest {
                pagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collect {
                binding.layoutRefresh.isRefreshing = it.isLoading()

                it.onError { t ->
                    pagingAdapter.retry()
                    Log.e(TAG, t.message, t)
                }
            }
        }
    }
}