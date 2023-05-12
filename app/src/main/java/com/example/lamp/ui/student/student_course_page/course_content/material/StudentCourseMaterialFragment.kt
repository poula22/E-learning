package com.example.lamp.ui.student.student_course_page.course_content.material

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseMaterialBinding
import com.example.lamp.ui.student.student_course_page.course_content.material.lessons_recycler_view.StudentCourseLessonsAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.PDFViewer
import com.example.lamp.ui.teacher.courses_page.course_content.material.VideoFullScreenFragment
import com.example.lamp.ui.teacher.courses_page.course_content.material.VideoItem
import com.example.lamp.ui.teacher.courses_page.course_content.material.YoutubeVideoFullScreenFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream


class StudentCourseMaterialFragment() : Fragment() {
    lateinit var viewBinding: FragmentStudentCourseMaterialBinding
    lateinit var viewModel: StudentCourseMaterialViewModel
    lateinit var adapter: StudentCourseLessonsAdapter
    lateinit var listener: AbstractYouTubePlayerListener
    lateinit var youTubePlayer: YouTubePlayer
    var path: String? = null
    var file: File? = null
    var pdfPath: String? = null
    var inputStream: InputStream? = null
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
        subScribeToLiveData()
        initViews()
        viewBinding.youtubePlayerView.isVisible = false
        viewBinding.videoPlayer.isVisible = false
        viewBinding.pdfIcon.isVisible = false
        viewBinding.assignmentTxt.isVisible = false
        viewBinding.contentTextHtml.isVisible = false
        viewModel.getCourseLessons()

        path = "https://www.youtube.com/watch?v=BGkL2Pq-g3A&list=RDBGkL2Pq-g3A&start_radio=1"
//        viewBinding.videoPlayer.setVideoPath(path)
    }

    private fun subScribeToLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                contentResponseDTO.get(0).let { content ->
//                    hideProgressBar()
                    viewBinding.videoPlayer.isVisible = content.videoPath != null
                    viewBinding.youtubePlayerView.isVisible = content.link != null
                    viewBinding.pdfIcon.isVisible = content.pdfPath != null
                    viewBinding.assignmentTxt.isVisible = content.pdfPath != null
                    viewBinding.contentTextHtml.isVisible = content.text != null
                    Log.v("contentResponseDTO", contentResponseDTO.toString())

                    content.videoPath?.let { it1 ->

                        if (content.videoPath != null) {
                            if (content.videoPath != "")
                                showProgressBar()
                        }
                        var str = it1.replace("\\\\Abanoub\\wwwroot\\Videos\\", "")
                        str = str.replace("\\", "/")
                        Log.e("str", str)
                        //63508411-35e9-4cc3-ad23-11df33cad213.mp4
                        //str.substring(str.lastIndexOf("/"))
                        viewModel.getVideo(str)
                    }

                    content.link?.let { it1 ->
                        path = it1
                        loadVideo(path!!)
                    }

                    content.pdfPath?.let { it1 ->
                        pdfPath = it1
                    }
                    content.text?.let { it1 ->
                        viewBinding.contentTextHtml.text = it1
                    }
                }
            }
//            if (it==null){
//                hideProgressBar()
//                viewBinding.youtubePlayerView.isVisible=false
//                viewBinding.videoPlayer.isVisible=false
//                viewBinding.pdfIcon.isVisible=false
//                viewBinding.assignmentTxt.isVisible=false
//                viewBinding.contentTextHtml.isVisible=false
//            }

        }
        viewModel.LessonsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateLessonsList(it)
            }
            hideProgressBar()
        }
        viewModel.VideoLiveData.observe(viewLifecycleOwner) {

            Log.e("VideoLiveData", it.toString())
            file =
                File.createTempFile(
                    "test",
                    ".mp4",
                    requireContext().cacheDir
                )
            FileUtils.copyInputStreamToFile(it.byteStream(), file)
//                viewBinding.videoPlayer.setVideoPath(file.absolutePa

            hideProgressBar()
        }
    }


    private fun initViews() {
//        viewBinding.videoPlayer.setMediaController(null)
//        val mediaController = MediaController(requireContext())
//        mediaController.setAnchorView(viewBinding.videoPlayer)
//        val uri: Uri = Uri.parse(
//            "android.resource://" + requireActivity().packageName + "/" + R.raw.dragon_ball
//        )
//        viewBinding.videoPlayer.setMediaController(mediaController)
//        viewBinding.videoPlayer.setVideoURI(uri)
//        viewBinding.videoPlayer.requestFocus()

///////////////////////////////////////////////////////////
//        viewBinding.videoPlayer.setOnPreparedListener {
//            //preparing
//            it.start()
//        }
        viewBinding.assignmentTxt.setOnClickListener {
            if (pdfPath != null) {
                val bundle = Bundle()
                val pdfViewer = PDFViewer()
                bundle.putString("pdf", pdfPath)
                pdfViewer.arguments = bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_course_content_container, pdfViewer)
                    .commit()
            }
        }

        viewBinding.pdfIcon.setOnClickListener {
            if (pdfPath != null) {
                val bundle = Bundle()
                val pdfViewer = PDFViewer()
                bundle.putString("pdf", pdfPath)
                pdfViewer.arguments = bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_course_content_container, pdfViewer)
                    .commit()
            }
        }

        viewBinding.videoPlayer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("video", file?.absolutePath)
            val fragment = VideoFullScreenFragment()
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.student_fragment_tab, fragment)
                .addToBackStack("")
                .commit()


        }
        val youTubePlayerView: YouTubePlayerView = viewBinding.youtubePlayerView

        lifecycle.addObserver(youTubePlayerView)
        listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {

                setVideoLoader(youTubePlayer)
                path?.let {
                    loadVideo(it)
                }
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                Log.e("error", error.name)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                if (state == PlayerConstants.PlayerState.PLAYING) {
                    path?.let {
                        val bundle = Bundle()
                        Log.v("path", it)
                        bundle.putSerializable("video", VideoItem(youTubePlayer, it))
                        val fragment = YoutubeVideoFullScreenFragment()
                        fragment.arguments = bundle
                        requireActivity().supportFragmentManager
                            .beginTransaction().addToBackStack("")
                            .replace(R.id.student_fragment_tab, fragment)
                            .commit()
                        youTubePlayer.pause()
                    }
                    youTubePlayer.removeListener(this)
                }
            }

        }
        youTubePlayerView.addYouTubePlayerListener(listener)

        // youtube player

        adapter = StudentCourseLessonsAdapter()
        adapter.onLessonClickListener = object : StudentCourseLessonsAdapter.OnLessonClickListener {
            override fun onLessonClick(lessonId: Int) {
                showProgressBar()
                viewModel.getLessonContent(lessonId)
            }


        }
        viewBinding.lessonsRecyclerView.adapter = adapter
        //        viewBinding.contentTextHtml.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)

    }


    private fun loadVideo(path: String) {
        youTubePlayer.cueVideo(playYoutubeVideo(path), 0f)
    }

    private fun setVideoLoader(youTubePlayer: YouTubePlayer) {
        this.youTubePlayer = youTubePlayer
    }


    private fun playYoutubeVideo(path: String? = null): String {
        if (path != null) {
            Log.e("path", path)
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

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayer.removeListener(listener)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun hideProgressBar() {
        viewBinding.greyBackground.visibility = View.GONE
        viewBinding.progressBar.visibility = View.GONE
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}
