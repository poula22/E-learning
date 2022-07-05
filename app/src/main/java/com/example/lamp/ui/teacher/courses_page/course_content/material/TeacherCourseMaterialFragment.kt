package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseMaterialBinding
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.TeacherCourseLessonsAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class TeacherCourseMaterialFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseMaterialBinding
    lateinit var viewModel: TeacherCourseMaterialViewModel
    lateinit var adapter: TeacherCourseLessonsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseMaterialViewModel::class.java)
    }

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
        subScribeToLiveData()
        initViews()
        viewModel.getCourseLessons(CONSTANTS.courseId)
    }

    private fun subScribeToLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                contentResponseDTO.forEach { content ->
                    if (content.path?.contains("https://www.youtube.com") == true) {
                        playYoutubeVideo(content.path!!)
                    } else if (content.fileName?.contains(".pdf") == true) {

                    } else if (content.fileName?.contains("text") == true) {

                    } else {
                        viewBinding.videoPlayer.setVideoPath(content.path)
                    }
                }
            }

        }
        viewModel.LessonsLiveData.observe(viewLifecycleOwner) {
            adapter.changeData(it)
        }
    }


    private fun initViews() {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(viewBinding.videoPlayer)
        val uri: Uri = Uri.parse(
            "android.resource://" + requireActivity().packageName + "/" + R.raw.dragon_ball
        )
        viewBinding.videoPlayer.setMediaController(mediaController)
        viewBinding.videoPlayer.setVideoURI(uri)
        viewBinding.videoPlayer.requestFocus()

        viewBinding.videoPlayer.setOnPreparedListener {
            //preparing
            it.start()
        }

        // youtube player
        playYoutubeVideo()
        adapter = TeacherCourseLessonsAdapter()
        adapter.onItemClickListener = object : TeacherCourseLessonsAdapter.OnItemClickListener {
            override fun onItemClick(lesson: LessonResponseDTO) {
                lesson.id?.let { viewModel.getLessonContent(it) }
            }

        }
        viewBinding.lessonsRecyclerView.adapter = adapter
        //        viewBinding.contentTextHtml.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)

        viewBinding.addLessonButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.teacher_course_content_container, TeacherCourseAddLessonFragment())
                .addToBackStack("")
                .commit()
        }

    }


    private fun playYoutubeVideo(path: String? = null) {
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
