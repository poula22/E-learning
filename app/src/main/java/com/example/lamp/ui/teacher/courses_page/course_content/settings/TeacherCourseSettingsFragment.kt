package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseSettingsBinding
import org.apache.commons.io.FileUtils
import java.io.File


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
            Log.v("filePath of image", it)
        }
        if (file != null) {
            viewModel.changeCourseImage(file)
        }
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
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
            viewModel.getImage(course.courseImage.toString())
//            var serverUrl="https://25.70.83.232"

//            val thumbnail: Bitmap? =
//                course.courseImage?.toUri()?.let { it1 ->
//                    requireContext().contentResolver.loadThumbnail(
//                        it1, Size(640, 480), null)
//                }
//            viewBinding.courseImageView.setImageBitmap(thumbnail)



//            with(this)
//            .load("file:///"+it.courseImage?.substring(2)?.replace("\\","/"))
//            .centerCrop()
//            .into(viewBinding.courseImageView)



//            var image=course.courseImage?.let { it1 -> TestConnection.getData(it1) }
//            val img= course.courseImage?.let { it1 -> File(it1) }
//            Log.e("image",img?.exists().toString())
        }
        viewModel.fileLiveData.observe(viewLifecycleOwner){
            viewModel.getCourseById()
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
        viewModel.testLiveData.observe(viewLifecycleOwner){
            viewBinding.courseImageView.setImageBitmap(BitmapFactory.decodeStream(it.byteStream()))
        }
        viewModel.updateLiveData.observe(viewLifecycleOwner){
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        viewBinding.item=course
        viewBinding.changeImageBtn.setOnClickListener {
            imagePick()
        }

        viewBinding.deleteImageBtn.setOnClickListener {
            //delete image from database
            var file =
                File.createTempFile(
                    "test",
                    ".png",
                    requireContext().cacheDir
                )
            val inputStream=resources.openRawResource(R.raw.light_bulb)
            FileUtils.copyInputStreamToFile(inputStream, file)

            viewModel.changeCourseImage(file)
        }


        viewBinding.saveBtn.setOnClickListener {
            val course=CourseResponseDTO(
                courseName = viewBinding.courseTitle.text.toString() ,
                teacherId = CONSTANTS.user_id,
                courseDescription = viewBinding.descriptionLayout.editText?.text.toString()
            )
            viewModel.updateCourse(course)

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


