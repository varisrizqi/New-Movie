package com.tipiz.moviexsis.ui.search

import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.core.utils.state.launchAndCollectIn
import com.tipiz.core.utils.state.onSuccess
import com.tipiz.moviexsis.databinding.FragmentSearchBinding
import com.tipiz.moviexsis.ui.bottomsheet.BottomSheetFragment
import com.tipiz.moviexsis.ui.home.adapter.NowPlayingAdapter
import com.tipiz.moviexsis.utils.BaseFragment
import com.tipiz.moviexsis.utils.Constant
import kotlinx.coroutines.flow.last
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding,SearchViewModel>(FragmentSearchBinding::inflate) {
    override val viewModel: SearchViewModel by viewModel()

    override fun initView() {
        with(binding) {
            toolbar.doAfterTextChanged {
                parentFragment?.viewLifecycleOwner?.let {
                    viewModel.showSearch(toolbar.text.toString())
                }
            }
        }


    }

    override fun initViewModel() {
        with(viewModel){
            responseSearch.launchAndCollectIn(viewLifecycleOwner){uiState ->
                uiState.onSuccess { data ->
                    setSearch(data)

                }
            }
        }
    }

    private fun setSearch(search: List<DataSearch>) {
        with(binding) {

            val adapter = SearchAdapter(object : SearchAdapter.OnPagingListener {
                override fun onClick(search: DataSearch) {
                    val btmSheet = BottomSheetFragment()
                    val bsBundle = bundleOf(
                        Constant.BUNDLE_KEY_ID to search.id,
                    )
                    btmSheet.arguments = bsBundle
                    btmSheet.show(childFragmentManager, Constant.extra_btm_sheet)
                }
            })
            rvSearch.adapter = adapter
            adapter.submitList(search)
            val layoutManager = GridLayoutManager(context,2)
            rvSearch.layoutManager = layoutManager
            rvSearch.setHasFixedSize(true)
        }
    }

}
