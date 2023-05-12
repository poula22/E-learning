package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.common_functions.CONSTANTS
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseMaterialBinding
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.PDFViewer
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.TeacherCourseLessonsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream


class TeacherCourseMaterialFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseMaterialBinding
    lateinit var viewModel: TeacherCourseMaterialViewModel
    lateinit var adapter: TeacherCourseLessonsAdapter
    lateinit var listener:AbstractYouTubePlayerListener
    var youTubePlayer: YouTubePlayer?=null
    var path:String? = null
    var file:File? = null
    var pdfPath:String? = null
    var inputStream:InputStream? = null
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
        viewBinding.youtubePlayerView.isVisible=false
        viewBinding.videoPlayer.isVisible=false
        viewBinding.pdfIcon.isVisible=false
        viewBinding.assignmentTxt.isVisible=false
        viewBinding.contentTextHtml.isVisible=false
        viewModel.getCourseLessons()
//        viewBinding.videoPlayer.setVideoPath(path)
    }

    private fun subScribeToLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                contentResponseDTO.get(0).let { content ->
                    hideProgressBar()
                    viewBinding.videoPlayer.isVisible = content.videoPath != null
                    viewBinding.youtubePlayerView.isVisible = content.link != null
                    viewBinding.pdfIcon.isVisible=content.pdfPath != null
                    viewBinding.assignmentTxt.isVisible=content.pdfPath != null
                    viewBinding.contentTextHtml.isVisible=content.text != null
                    Log.v("contentResponseDTO", contentResponseDTO.toString())

                    content.videoPath?.let { it1 ->

                        if (content.videoPath!=null ){
                            if (content.videoPath!="")
                                showProgressBar()
                        }
                        var str=it1.replace("\\\\Abanoub\\wwwroot\\Videos\\","")
                        str=str.replace("\\","/")
                        Log.e("str",str)
                        //63508411-35e9-4cc3-ad23-11df33cad213.mp4
                        //str.substring(str.lastIndexOf("/"))
                        viewModel.getVideo(str)
                    }

                    content.link?.let {
                            it1 -> path= it1
                            loadVideo(path!!)
                    }

                    content.pdfPath?.let { it1 ->
                        pdfPath=it1
                    }
                    content.text?.let { it1 ->
                        viewBinding.contentTextHtml.text=it1
                    }
                }
            }
            if(it==null)
                hideProgressBar()
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
                adapter.changeData(it)
            }
            hideProgressBar()
        }
        viewModel.VideoLiveData.observe(viewLifecycleOwner){

                Log.e("VideoLiveData",it.toString())
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
        viewModel.removeLessonLiveData.observe(viewLifecycleOwner) {
            viewModel.getCourseLessons()
            Toast.makeText(requireContext(), "deleted successfully", Toast.LENGTH_SHORT).show()
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
            if(pdfPath!=null){
                val bundle=Bundle()
                val pdfViewer=PDFViewer()
                bundle.putString("pdf",pdfPath)
                pdfViewer.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.teacher_course_content_container,pdfViewer)
                    .commit()
            }
        }

        viewBinding.pdfIcon.setOnClickListener {
            if(pdfPath!=null){
                val bundle=Bundle()
                val pdfViewer= PDFViewer()
                bundle.putString("pdf",pdfPath)
                pdfViewer.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.teacher_course_content_container,pdfViewer)
                    .commit()
            }
        }

        viewBinding.videoPlayer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("video", file?.absolutePath)
            val fragment=VideoFullScreenFragment()
            fragment.arguments=bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.teacher_fragment_tab,fragment)
                .addToBackStack("")
                .commit()


        }
        val youTubePlayerView: YouTubePlayerView = viewBinding.youtubePlayerView

        lifecycle.addObserver(youTubePlayerView)
        listener=object : AbstractYouTubePlayerListener() {
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
                if (state==PlayerConstants.PlayerState.PLAYING){
                    path?.let {
                        val bundle=Bundle()
                        Log.v("path",it)
                        bundle.putSerializable("video",VideoItem(youTubePlayer,it))
                        val fragment = YoutubeVideoFullScreenFragment()
                        fragment.arguments=bundle
                        requireActivity().supportFragmentManager
                            .beginTransaction().addToBackStack("")
                            .replace(R.id.teacher_fragment_tab,fragment)
                            .commit()
                        youTubePlayer.pause()
                    }
                    youTubePlayer.removeListener(this)
                }
            }

        }
        youTubePlayerView.addYouTubePlayerListener(listener)

        // youtube player

        adapter = TeacherCourseLessonsAdapter()
        adapter.onItemClickListener = object : TeacherCourseLessonsAdapter.OnItemClickListener {
            override fun onItemClick(lesson: LessonResponseDTO) {
                lesson.id?.let {
                    showProgressBar()
                    viewModel.getLessonContent(it)
                }
            }

            override fun onEditClick(lesson: LessonResponseDTO) {
//                lesson.id?.let { viewModel.updateLesson(it,lesson) }
                val bundle = Bundle()
                bundle.putSerializable("lesson", lesson)
                val fragment = TeacherCourseEditMaterialFragment()
                fragment.arguments = bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab,fragment)
                    .addToBackStack("")
                    .commit()
            }

        }
        viewBinding.lessonsRecyclerView.adapter = adapter
        //        viewBinding.contentTextHtml.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)

        viewBinding.addLessonButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack("")
                .replace(R.id.teacher_fragment_tab, TeacherCourseAddLessonFragment())
                .commit()
        }

        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete this todo?")
                    .setPositiveButton("Yes") { dialog, which ->

                        val position = viewHolder.absoluteAdapterPosition
                        Log.v("position", position.toString())
                        val lesson = adapter.lessonList?.get(position)
                        Log.v("assignment", lesson?.id.toString())
                        viewModel.removeLesson(lesson)
                    }
                    .setNegativeButton("No") { dialog, which ->
                        adapter.notifyItemChanged(viewHolder.absoluteAdapterPosition)
                    }
                    .show()

            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(viewBinding.lessonsRecyclerView)

    }

    private fun loadVideo(path: String) {
        youTubePlayer?.cueVideo(playYoutubeVideo(path), 0f)
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

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayer?.removeListener(listener)
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
