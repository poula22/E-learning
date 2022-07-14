package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.common_functions.CONSTANTS
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseMaterialBinding
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.TeacherCourseLessonsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.apache.commons.io.FileUtils
import java.io.File


class TeacherCourseMaterialFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseMaterialBinding
    lateinit var viewModel: TeacherCourseMaterialViewModel
    lateinit var adapter: TeacherCourseLessonsAdapter
    lateinit var listener:AbstractYouTubePlayerListener
    lateinit var youTubePlayer: YouTubePlayer
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
        viewModel.getCourseLessons()
    }

    private fun subScribeToLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            it?.let { contentResponseDTO ->
                contentResponseDTO.forEach { content ->
                    Log.v("contentResponseDTO", contentResponseDTO.toString())
                    content.link?.let { it1 -> loadVideo(it1) }
                    content.videoPath?.let { it1 ->
                        var str=it1.replace("\\\\Abanoub\\wwwroot\\"," https://25.70.83.232:7097/")
                        str=str.replace("\\","/")
                        Log.e("str",str)
                        //63508411-35e9-4cc3-ad23-11df33cad213.mp4
                        //str.substring(str.lastIndexOf("/"))
                        viewModel.getVideo("63508411-35e9-4cc3-ad23-11df33cad213.mp4")
                         }
                }
            }

        }
        viewModel.LessonsLiveData.observe(viewLifecycleOwner) {
            adapter.changeData(it)
        }
        viewModel.VideoLiveData.observe(viewLifecycleOwner){
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
        viewModel.removeLessonLiveData.observe(viewLifecycleOwner) {
            viewModel.getCourseLessons()
            Toast.makeText(requireContext(), "deleted successfully", Toast.LENGTH_SHORT).show()
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

//        viewBinding.videoPlayer.setOnPreparedListener {
//            //preparing
//            it.start()
//        }
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
                lesson.id?.let { viewModel.getLessonContent(it) }
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
        youTubePlayer.cueVideo(playYoutubeVideo(path), 0f)
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
        youTubePlayer.removeListener(listener)
    }

}
