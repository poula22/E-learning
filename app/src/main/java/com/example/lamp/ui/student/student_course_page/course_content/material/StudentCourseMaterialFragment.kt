package com.example.lamp.ui.student.student_course_page.course_content.material

import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseMaterialBinding
import com.example.lamp.ui.student.student_course_page.course_content.material.lessons_recycler_view.StudentCourseLessonsAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class StudentCourseMaterialFragment() : Fragment() {
    lateinit var viewBinding: FragmentStudentCourseMaterialBinding
    val courseId: Int = CONSTANTS.courseId
    lateinit var viewModel: StudentCourseMaterialViewModel
    val adapter =
        StudentCourseLessonsAdapter(mutableListOf(LessonResponseDTO("desc1", 1, "lesson1", 1)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentCourseMaterialViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_course_material, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        getCoursesLessons()
    }

    private fun getCoursesLessons() {
        viewModel.getCourseLessons(courseId)
    }

    private fun initViews() {
        //play teacher video
        playTeacherVideo()
        // youtube player
        playYoutubeVideo()
        adapter.onLessonClickListener = object : StudentCourseLessonsAdapter.OnLessonClickListener {
            override fun onLessonClick(lessonId: Int) {
                getCourseLessonContent(lessonId)
            }
        }
        viewBinding.lessonsRecyclerView.adapter = adapter
        //        viewBinding.contentTextHtml.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }

    private fun getCourseLessonContent(lessonId: Int) {
        viewModel.getLessonContent(lessonId)
    }

    private fun playTeacherVideo(path: String? = null) {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(viewBinding.videoPlayer)

        val lp: FrameLayout.LayoutParams =
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        lp.gravity = Gravity.BOTTOM
        mediaController.layoutParams = lp
        (mediaController.getParent() as ViewGroup).removeView(mediaController)
        viewBinding.videoFrame.addView(mediaController)

        val uri: Uri = Uri.parse(
            "android.resource://" + requireActivity().packageName + "/" + R.raw.dragon_ball
        )
        viewBinding.videoPlayer.setMediaController(mediaController);
        viewBinding.videoPlayer.setVideoURI(uri);
        viewBinding.videoPlayer.requestFocus();
//        viewBinding.videoPlayer.start();
    }

    private fun subscribeToLiveData() {
        viewModel.LessonsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                updateLessonsList(it)
            }
        }
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                contentResponseDTO.forEach { content ->
                    if (content.path?.contains("https://www.youtube.com") == true) {
                        playYoutubeVideo(content.path!!)
                    } else if (content.fileName?.contains(".pdf") == true) {

                    } else if (content.fileName?.contains("text") == true) {

                    } else {
                        playTeacherVideo(content.path!!)
                    }
                }
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.errorMessage.value = it
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateLessonsList(it: List<LessonResponseDTO>) {
        adapter.updateLessonsList(it)
    }


    private fun playYoutubeVideo(youtubeUrl: String? = null) {
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
