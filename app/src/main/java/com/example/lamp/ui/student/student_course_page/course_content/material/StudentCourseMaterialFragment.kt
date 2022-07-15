package com.example.lamp.ui.student.student_course_page.course_content.material

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.isVisible
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
import org.apache.commons.io.FileUtils
import java.io.File


class StudentCourseMaterialFragment() : Fragment() {
    lateinit var viewBinding: FragmentStudentCourseMaterialBinding
    val courseId: Int = CONSTANTS.courseId
    lateinit var viewModel: StudentCourseMaterialViewModel
    val adapter =
        StudentCourseLessonsAdapter(mutableListOf(LessonResponseDTO("desc1", 1, "lesson1", 1)))

    lateinit var listener:AbstractYouTubePlayerListener
    var youTubePlayer: YouTubePlayer?= null
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
        showProgressBar()
        getCoursesLessons()
    }

    private fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun getCoursesLessons() {
        viewModel.getCourseLessons(courseId)
    }

    private fun initViews() {
        //play teacher video
        playTeacherVideo()
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
        playYoutubeVideo()
        adapter.onLessonClickListener = object : StudentCourseLessonsAdapter.OnLessonClickListener {
            override fun onLessonClick(lessonId: Int) {
                showProgressBar()
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
            hideProgressBar()
        }
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                hideProgressBar()
                contentResponseDTO.forEach{ content ->
                    if (content.videoPath!=null ){
                        if (content.videoPath!="")
                            showProgressBar()
                    }


                    Log.v("contentResponseDTO", contentResponseDTO.toString())
                    content.link?.let { it1 -> loadVideo(it1) }
                    content.videoPath?.let { it1 ->
                        var str=it1.replace("\\\\Abanoub\\wwwroot\\Videos\\","")
                        str=str.replace("\\","/")
                        Log.e("str",str)
                        //63508411-35e9-4cc3-ad23-11df33cad213.mp4
                        //str.substring(str.lastIndexOf("/"))

                        viewModel.getVideo(str)
                    }

                }
            }

        }
        viewModel.VideoLiveData.observe(viewLifecycleOwner){
            it?.let {
                Log.e("VideoLiveData",it.toString())
                val file =
                    File.createTempFile(
                        "test",
                        ".mp4",
                        requireContext().cacheDir
                    )
                FileUtils.copyInputStreamToFile(it.byteStream(), file)
                viewBinding.videoPlayer.setVideoPath(file.absolutePath)
            }
            hideProgressBar()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.errorMessage.value = it
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideProgressBar() {
        viewBinding.greyBackground.visibility = View.GONE
        viewBinding.progressBar.visibility = View.GONE
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun updateLessonsList(it: List<LessonResponseDTO>) {
        adapter.updateLessonsList(it)
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
    private fun loadVideo(path: String) {
        youTubePlayer?.cueVideo(playYoutubeVideo(path), 0f)
    }

    private fun setVideoLoader(youTubePlayer: YouTubePlayer,) {
        this.youTubePlayer = youTubePlayer
    }

}
