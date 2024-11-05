package com.tipiz.moviexsis.ui.home

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.core.utils.state.launchAndCollectIn
import com.tipiz.core.utils.state.onError
import com.tipiz.core.utils.state.onSuccess
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.FragmentHomeBinding
import com.tipiz.moviexsis.ui.bottomsheet.BottomSheetFragment
import com.tipiz.moviexsis.ui.home.adapter.NowPlayingAdapter
import com.tipiz.moviexsis.ui.home.adapter.PopularAdapter
import com.tipiz.moviexsis.ui.home.adapter.TopRatedAdapter
import com.tipiz.moviexsis.utils.BaseFragment
import com.tipiz.moviexsis.utils.Constant.BUNDLE_KEY_ID
import com.tipiz.moviexsis.utils.Constant.extra_btm_sheet
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()


    override fun initView() {

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }



    override fun initViewModel() {
        with(viewModel) {
            showPopular(1)
            responsePopular.launchAndCollectIn(viewLifecycleOwner) { uiState ->
                uiState.onSuccess { data ->
                    setUpPopular(data)
                }

            }

            showTopRated(1)
            responseTopRated.launchAndCollectIn(viewLifecycleOwner){ uiState ->
                uiState.onSuccess { data ->
                    setUpTopRated(data)
                }
                uiState.onError {
                    Toast.makeText(context, "ini error", Toast.LENGTH_SHORT).show()
                }
            }

            showNowPlaying(1)
            responseNowPlaying.launchAndCollectIn(viewLifecycleOwner){ uiState ->
                uiState.onSuccess { data ->
                    setUpNowPlaying(data)
                }
                uiState.onError {
                    Toast.makeText(context, "ini error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun setUpPopular(popular: List<DataPopular>) {

        with(binding) {

            val adapter = PopularAdapter(popular, object : PopularAdapter.OnPagingListener {
                override fun onClick(popular: DataPopular) {
                    val btmSheet = BottomSheetFragment()
                    val bsBundle = bundleOf(
                        BUNDLE_KEY_ID to popular.id,
                    )
                    btmSheet.arguments = bsBundle
                    btmSheet.show(childFragmentManager, extra_btm_sheet)
                }
            })
            rvPopular.adapter = adapter
            TabLayoutMediator(binding.tabHomeTabView, binding.rvPopular) { _, _ -> }.attach()
            (requireActivity() as AppCompatActivity).supportActionBar?.elevation = 0f

        }
    }

    private fun setUpTopRated(topRated: List<DataTopRated>) {

        with(binding) {

            val adapter = TopRatedAdapter(object : TopRatedAdapter.OnPagingListener {
                override fun onClick(topRated: DataTopRated) {
                    val btmSheet = BottomSheetFragment()
                    val bsBundle = bundleOf(
                        BUNDLE_KEY_ID to topRated.id,
                    )
                    btmSheet.arguments = bsBundle
                    btmSheet.show(childFragmentManager, extra_btm_sheet)
                }
            })
            rvTopRated.adapter = adapter
            adapter.submitList(topRated)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvTopRated.layoutManager = layoutManager
            rvTopRated.setHasFixedSize(true)
        }
    }

    private fun setUpNowPlaying(np: List<DataNowPlaying>) {

        with(binding) {

            val adapter = NowPlayingAdapter(object : NowPlayingAdapter.OnPagingListener {
                override fun onClick(np: DataNowPlaying) {
                    val btmSheet = BottomSheetFragment()
                    val bsBundle = bundleOf(
                        BUNDLE_KEY_ID to np.id,
                    )
                    btmSheet.arguments = bsBundle
                    btmSheet.show(childFragmentManager, extra_btm_sheet)
                }
            })
            rvNowPlaying.adapter = adapter
            adapter.submitList(np)
            val layoutManager = GridLayoutManager(context,2)
            rvNowPlaying.layoutManager = layoutManager
            rvNowPlaying.setHasFixedSize(true)
        }
    }

}