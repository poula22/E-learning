package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.binding_adapters.TestConnection
import com.example.common_functions.CommonFunctions
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseSettingsBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL


class TeacherCourseSettingsFragment : ExternalStorageAccessFragment() {

    lateinit var viewBinding: FragmentTeacherCourseSettingsBinding
    lateinit var course: CourseResponseDTO
    lateinit var viewModel: TeacherCourseSettingsViewModel
    override fun showProgressBar() {

    }

    override fun resultListener(byteArray: ByteArray) {
        val file= filePath?.let {
            File(it)
        }
        filePath?.let {
            Log.e("filePath of image", it)
        }
        if (file != null) {
            viewModel.changeCourseImage(file)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCourseSettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_settings,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        course=requireArguments().getSerializable("course") as CourseResponseDTO
        subscribeToLiveData()
        initViews()
        viewModel.getCourseById()
//        viewModel.callResult=object :TeacherCourseSettingsViewModel.CallResult{
//            override fun getDTOData(data: CourseResponseDTO) {
//
//            }
//
//        }
    }

    private fun subscribeToLiveData() {

        viewModel.liveData.observe(viewLifecycleOwner){
            course=it
            Log.e("course",course.courseImage.toString())
            viewBinding.item=it
            var serverUrl="25.70.83.232"
            Glide.with(this)
                .load("https://thumbs.dreamstime.com/b/lonely-elephant-against-sunset-beautiful-sun-clouds-savannah-serengeti-national-park-africa-tanzania-artistic-imag-image-106950644.jpg".toUri())
                .centerCrop()
                .into(viewBinding.courseImageView)
//            var image=course.courseImage?.let { it1 -> TestConnection.getData(it1) }
//            val img= course.courseImage?.let { it1 -> File(it1) }
//            Log.e("image",img?.exists().toString())
        }

        viewModel.dropLiveData.observe(viewLifecycleOwner) {
            if (it.code() == 200) {
                Toast.makeText(requireContext(), "Course Dropped", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            }
            else{
                Toast.makeText(requireContext(), "Course Not Dropped", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        viewBinding.item=course
        viewBinding.changeImageBtn.setOnClickListener {
            imagePick()
        }

        viewBinding.deleteImageBtn.setOnClickListener {
            viewBinding.courseImageView.setImageResource(R.drawable.ic_courses)
            //delete image from database
        }


        viewBinding.saveBtn.setOnClickListener {
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewBinding.courseCodeLinearLayout.setOnClickListener {
            CommonFunctions.copyTextToClipboard(
                viewBinding.courseCodeTextView.text.toString(),
                requireContext()
            )
        }
        viewBinding.dropBtn.setOnClickListener{
            viewModel.dropCourse()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }



}


