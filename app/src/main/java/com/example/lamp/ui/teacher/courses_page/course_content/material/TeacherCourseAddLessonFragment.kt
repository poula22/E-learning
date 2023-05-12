package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions.Companion.calendar
import com.example.common_functions.CommonFunctions.Companion.showDatePicker
import com.example.common_functions.DocumentAccessFragment
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddSectionBinding
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream
import java.util.*


class TeacherCourseAddLessonFragment : DocumentAccessFragment() {
    lateinit var viewBinding: FragmentTeacherCourseAddSectionBinding
    lateinit var viewModel: TeacherCourseAddLessonViewModel
    private var flag:Boolean=false
    private var lessonId =-1
    var pdfFile:File?=null
    private var pdfFlag=false
    var videofile:File?=null
    private var videoFlag=false
    private var inputStreamPdf: InputStream?=null
    private var inputStreamVideo: InputStream?=null
    override fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    override fun resultListener(byteArray: ByteArray) {
        try {
            if (pdfFlag && inputStreamPdf == null) {
                inputStreamPdf=fileUri?.let { requireActivity().contentResolver.openInputStream(it) }
            }
            if (videoFlag && inputStreamVideo == null) {
                inputStreamVideo=fileUri?.let { requireActivity().contentResolver.openInputStream(it) }
            }

        }catch (e:Exception){
            Log.v("Exception",e.toString())
        }
        hideProgressBar()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseAddLessonViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_add_section,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
    }

    private fun subscribeToLiveData() {
        viewModel.lessonLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show()
            flag=true
            lessonId=it.id!!
            //change 21 to lessonId
            val content= ContentResponseDTO(
                "",
                "",
                link = viewBinding.youtubeLink.text.toString(),
                it.id,
                0,
                viewBinding.description.text.toString(),
                "2022-07-05T15:42:32.723Z"
            )
            assignData()
            viewModel.addContent(content,pdfFile,videofile)
        }
        viewModel.contentLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        viewBinding.addVideoBtn.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "video/*"
//            startActivityForResult(intent, CONSTANTS.VIDEO_REQUEST_CODE)
            videoFlag=true
            uploadVideo()
        }

        viewBinding.attachFileBtn.setOnClickListener {
            pdfFlag=true
            uploadDoc()
        }

        viewBinding.createBtn.setOnClickListener {
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            //insert in database
            if (!flag){
                val lesson=LessonResponseDTO(
                    viewBinding.description.text.toString(),
                    0,
                    viewBinding.sectionTitle.text.toString(),
                    CONSTANTS.courseId)
                viewModel.addLesson(lesson)
            }
            if (lessonId!=-1){
                val content= ContentResponseDTO(
                    this.videofile?.absolutePath,
                    this.pdfFile?.absolutePath,
                link = viewBinding.youtubeLink.text.toString(),
                lessonId,
                0,
                viewBinding.description.text.toString(),
                "2022-07-05T15:42:32.723Z"
                )
                assignData()
                viewModel.addContent(content,pdfFile,videofile)
            }


        }

        viewBinding.publishDate.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH)
                    + "/" + calendar.get(Calendar.MONTH)
                    + "/" + calendar.get(Calendar.YEAR)
        )
        viewBinding.publishDate.setOnClickListener {
            showDatePicker(viewBinding.publishDate, requireContext())
        }

        val tab=arguments?.getString("tab")
        if (tab=="lesson") {
            viewBinding.addVideoBtn.isVisible=false
            viewBinding.attachFileBtn.isVisible=false
            viewBinding.createBtn.isVisible=false
            viewBinding.youtubeLink.isVisible=false

            viewBinding.sectionTitle.isVisible=true
            viewBinding.description.isVisible=true

            viewBinding.publishDate.isVisible=false
            viewBinding.startDate.isVisible=false
        }else if (tab=="content"){
            viewBinding.addVideoBtn.isVisible=true
            viewBinding.addVideoBtn.text="Edit Video"
            viewBinding.attachFileBtn.isVisible=true
            viewBinding.attachFileBtn.text="Edit File"
            viewBinding.createBtn.isVisible=false
            viewBinding.youtubeLink.isVisible=true
            viewBinding.youtubeLink.hint="Edit youtube link"

            viewBinding.sectionTitle.isVisible=false
            viewBinding.description.isVisible=false
            viewBinding.publishDate.isVisible=false
            viewBinding.startDate.isVisible=false
        }

    }


    private fun hideProgressBar() {
        viewBinding.greyBackground.visibility = View.GONE
        viewBinding.progressBar.visibility = View.GONE
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if ((requestCode == CONSTANTS.FILE_REQUEST_CODE || requestCode == CONSTANTS.VIDEO_REQUEST_CODE) && resultCode == RESULT_OK) {
//            val selectedFile = data?.data //The uri with the location of the file
//            val selectedFilePath = data?.data?.path
//        }
//    }

    // we will use it to play video on video view on content part
    fun playVideoInDevicePlayer(videoPath: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoPath))
        intent.setDataAndType(Uri.parse(videoPath), "video/*")
        startActivity(intent)
    }
    fun assignData(){

        showProgressBar()
            if (pdfFlag){
            pdfFile =
                File.createTempFile(
                    "test",
                    ".pdf",
                    requireContext().cacheDir
                )
            pdfFlag=false
            FileUtils.copyInputStreamToFile(inputStreamPdf, pdfFile)

        }

        if (videoFlag){
            videofile=
                File.createTempFile(
                    "video",
                    ".mp4",
                    requireContext().cacheDir
                )
            videoFlag=false
            FileUtils.copyInputStreamToFile(inputStreamVideo, videofile)
        }
    hideProgressBar()


    }

    //    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                if (data?.data != null) {
//                    val uriPathHelper = URIPathHelper()
//                    val videoFullPath = uriPathHelper.getPath(requireContext(), data.data!!)
//                    if (videoFullPath != null) {
//                        val file = File(videoFullPath)
//                    }
//                }
//            }
//        } else if (requestCode == 2) {
//            if (resultCode == Activity.RESULT_OK) {
//                if (data?.data != null) {
//                    val uriPathHelper = URIPathHelper()
//                    val fileFullPath = uriPathHelper.getPath(requireContext(), data?.data!!)
//                    if (fileFullPath != null) {
//                        val file = File(fileFullPath)
//                    }
//                }
//            }
//        }
//    }

}


//class URIPathHelper {
//
//    fun getPath(context: Context, uri: Uri): String? {
//        val isKitKatorAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
//
//        // DocumentProvider
//        if (isKitKatorAbove && DocumentsContract.isDocumentUri(context, uri)) {
//            // ExternalStorageProvider
//            if (isExternalStorageDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                if ("primary".equals(type, ignoreCase = true)) {
//                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
//                }
//
//            } else if (isDownloadsDocument(uri)) {
//                val id = DocumentsContract.getDocumentId(uri)
//                val contentUri = ContentUris.withAppendedId(
//                    Uri.parse("content://downloads/public_downloads"),
//                    java.lang.Long.valueOf(id)
//                )
//                return getDataColumn(context, contentUri, null, null)
//            } else if (isMediaDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                var contentUri: Uri? = null
//                if ("image" == type) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                } else if ("video" == type) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                } else if ("audio" == type) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                }
//                val selection = "_id=?"
//                val selectionArgs = arrayOf(split[1])
//                return getDataColumn(context, contentUri, selection, selectionArgs)
//            }
//        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
//            return getDataColumn(context, uri, null, null)
//        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
//            return uri.path
//        }
//        return null
//    }
//
//    fun getDataColumn(
//        context: Context,
//        uri: Uri?,
//        selection: String?,
//        selectionArgs: Array<String>?
//    ): String? {
//        var cursor: Cursor? = null
//        val column = "_data"
//        val projection = arrayOf(column)
//        try {
//            cursor = context.getContentResolver()
//                .query(uri!!, projection, selection, selectionArgs, null)
//            if (cursor != null && cursor.moveToFirst()) {
//                val column_index: Int = cursor.getColumnIndexOrThrow(column)
//                return cursor.getString(column_index)
//            }
//        } finally {
//            if (cursor != null) cursor.close()
//        }
//        return null
//    }
//
//    fun isExternalStorageDocument(uri: Uri): Boolean {
//        return "com.android.externalstorage.documents" == uri.authority
//    }
//
//    fun isDownloadsDocument(uri: Uri): Boolean {
//        return "com.android.providers.downloads.documents" == uri.authority
//    }
//
//    fun isMediaDocument(uri: Uri): Boolean {
//        return "com.android.providers.media.documents" == uri.authority
//    }
//}