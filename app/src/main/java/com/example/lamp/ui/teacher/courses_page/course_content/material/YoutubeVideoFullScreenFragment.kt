package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentYoutubeVideoFullScreenBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeVideoFullScreenFragment:Fragment() {
    lateinit var viewBinding: FragmentYoutubeVideoFullScreenBinding
    lateinit var listener:AbstractYouTubePlayerListener
    var path: String?=null
    var youTubePlayer:YouTubePlayer?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_youtube_video_full_screen, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoItem= requireArguments().getSerializable("video") as VideoItem?
        path=videoItem?.path
        youTubePlayer=videoItem?.youTubePlayer
        initViews()
        Log.v("log",path!!)
        path?.let { loadVideo(it) }
    }

    private fun initViews() {
        val youTubePlayerView: YouTubePlayerView = viewBinding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        listener=object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                setVideo(youTubePlayer)
                path?.let {
                    loadVideo(it)
                }
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                Log.e("error", error.name)
            }

        }
        youTubePlayerView.addYouTubePlayerListener(listener)
    }

    private fun setVideo(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer=youTubePlayer
    }

    private fun loadVideo(path: String) {
        youTubePlayer?.loadVideo(playYoutubeVideo(path), 0f)
    }
    private fun playYoutubeVideo(path: String? = null):String {
        if (path!=null){
            Log.e("path",path)
            val videoId =
                getYoutubeVideoId(path)
            return videoId
        }
        return ""
    }
    private fun getYoutubeVideoId(youtubeUrl: String): String {
        var index = youtubeUrl.indexOf("v=")
        return youtubeUrl.substring(index.plus(2), index.plus(13))
    }

}