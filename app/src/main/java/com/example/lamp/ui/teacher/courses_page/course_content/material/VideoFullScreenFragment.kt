package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentVideoFullScreenBinding

class VideoFullScreenFragment:Fragment() {
    lateinit var viewBinding:FragmentVideoFullScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_video_full_screen,container,false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val mediaController = MediaController(requireActivity().baseContext)
        mediaController.setAnchorView(viewBinding.videoPlayer)
        val path=requireArguments().getString("video")
        Log.v("path",path.toString())
        viewBinding.videoPlayer.setMediaController(mediaController)
        viewBinding.videoPlayer.keepScreenOn = true
        viewBinding.videoPlayer.setVideoPath(path)
        viewBinding.videoPlayer.requestFocus()
        viewBinding.videoPlayer.start()

    }



}
