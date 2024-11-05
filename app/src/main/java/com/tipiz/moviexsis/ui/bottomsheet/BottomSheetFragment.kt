package com.tipiz.moviexsis.ui.bottomsheet

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.tipiz.core.data.network.response.detail.GenresItem
import com.tipiz.core.domain.model.detail.DetailGenresItem
import com.tipiz.core.utils.state.launchAndCollectIn
import com.tipiz.core.utils.state.onError
import com.tipiz.core.utils.state.onSuccess
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.FragmentBottomSheetBinding
import com.tipiz.moviexsis.utils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private val viewModel: BottomSheetViewModel by viewModel()
    private lateinit var youTubePlayer: YouTubePlayer

    //untuk fitur fullscreen
    private var isFullscreen = false
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullscreen) {
                // if the player is in fullscreen, exit fullscreen
                youTubePlayer.toggleFullscreen()
            } else {
                requireActivity().finish()
            }
        }
    }

    private var cueKey: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(Constant.BUNDLE_KEY_ID) ?: 0
        viewModel.detailMovie(id)
        viewModel.fetchTrailerMovie(id)
        initView()
        youTube()


    }

    private fun initView() {
        with(binding) {

            viewModel.responseDetail.launchAndCollectIn(viewLifecycleOwner) { uiState ->
                uiState.onSuccess { data ->
                    tvTitle.text = data.originalTitle
                    tvDesc.text = data.overview
                    tvRating.text = data.voteAverage.toString()
                    val genresList: List<DetailGenresItem> = data.genres
                    val genresText = genresList.joinToString { it.name }
                    tvGenre.text = genresText.ifEmpty { "No genres available" }
                }
            }

            viewModel.responseTrailer.launchAndCollectIn(viewLifecycleOwner) { uiState ->
                uiState.onSuccess { data ->
                    cueKey = data.results.firstOrNull()?.key.orEmpty()
                }
            }

            imgClose.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun youTube() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        val youTubePlayerView = view?.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        val fullscreenViewContainer =
            view?.findViewById<FrameLayout>(R.id.full_screen_view_container)

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            //.fullscreen(1) // enable full screen button
            .autoplay(0)
            .build()

        // we need to initialize manually in order to pass IFramePlayerOptions to the player
        youTubePlayerView?.enableAutomaticInitialization = false

        youTubePlayerView?.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullscreen = true

                // the video will continue playing in fullscreenView
                youTubePlayerView.visibility = View.GONE
                fullscreenViewContainer?.visibility = View.VISIBLE
                fullscreenViewContainer?.addView(fullscreenView)

                // optionally request landscape orientation
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onExitFullscreen() {
                isFullscreen = false

                // the video will continue playing in the player
                youTubePlayerView?.visibility = View.VISIBLE
                fullscreenViewContainer?.visibility = View.GONE
                fullscreenViewContainer?.removeAllViews()
            }
        })

        youTubePlayerView?.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@BottomSheetFragment.youTubePlayer = youTubePlayer
                cueKey?.let {
                    this@BottomSheetFragment.youTubePlayer.cueVideo(it, 0f)
                }
            }
        }, iFramePlayerOptions)

        lifecycle.addObserver(youTubePlayerView!!)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        if (bottomSheetDialog is BottomSheetDialog) {
            bottomSheetDialog.behavior.skipCollapsed = true
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

}