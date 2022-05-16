package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseMaterialBinding
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class TeacherCourseMaterialFragment(var course: CourseItem?) : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseMaterialBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_material, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        // youtube player
        playYoutubeVideo()


//        viewBinding.contentTextHtml.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)


    }


    private fun playYoutubeVideo() {
        val youTubePlayerView: YouTubePlayerView = viewBinding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        val videoId =
            getYoutubeVideoId("https://www.youtube.com/watch?v=TiGgOmRBap4&list=RDCLAK5uy_m5j6nB_IPvvtoiErZo8aUsj_8pcUHLqUQ&index=27")
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    private fun getYoutubeVideoId(youtubeUrl: String): String {
        var index = youtubeUrl.indexOf("v=")
        return youtubeUrl.substring(index.plus(2), index.plus(13))
    }

}
