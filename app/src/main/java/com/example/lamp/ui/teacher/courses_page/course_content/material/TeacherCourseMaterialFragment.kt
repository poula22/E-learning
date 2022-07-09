package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.net.Uri
import android.os.Bundle
import android.util.Log
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
    lateinit var listener:AbstractYouTubePlayerListener
    lateinit var youTubePlayer: YouTubePlayer

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
                    Log.v("contentResponseDTO", contentResponseDTO.toString())
                    content.link?.let { it1 -> loadVideo(it1) }
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
        val youTubePlayerView: YouTubePlayerView = viewBinding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        listener=object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                setVideoLoader(youTubePlayer)
                loadVideo("https://www.youtube.com/watch?v=BGkL2Pq-g3A&list=RDBGkL2Pq-g3A&start_radio=1")
            }
        }
        youTubePlayerView.addYouTubePlayerListener(listener)

        // youtube player

        adapter = TeacherCourseLessonsAdapter()
        adapter.onItemClickListener = object : TeacherCourseLessonsAdapter.OnItemClickListener {
            override fun onItemClick(lesson: LessonResponseDTO) {
                lesson.id?.let { viewModel.getLessonContent(21) }
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

    private fun loadVideo(path: String) {
        youTubePlayer.loadVideo(playYoutubeVideo(path), 0f)
    }

    private fun setVideoLoader(youTubePlayer: YouTubePlayer,) {
        this.youTubePlayer = youTubePlayer
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
