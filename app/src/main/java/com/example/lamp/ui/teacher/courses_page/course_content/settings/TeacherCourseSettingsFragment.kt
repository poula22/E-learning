package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CommonFunctions
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.data.api.ApiManager
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseSettingsBinding
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.security.cert.X509Certificate
import javax.net.ssl.*


class TeacherCourseSettingsFragment : ExternalStorageAccessFragment() {

    lateinit var viewBinding: FragmentTeacherCourseSettingsBinding
    lateinit var course: CourseResponseDTO
    lateinit var viewModel: TeacherCourseSettingsViewModel
    override fun showProgressBar() {

    }

    override fun resultListener(byteArray: ByteArray) {
        val file = filePath?.let {
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
        viewModel = ViewModelProvider(this).get(TeacherCourseSettingsViewModel::class.java)
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
        course = requireArguments().getSerializable("course") as CourseResponseDTO
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


//////////////////////// getting

    fun getUnsafeOkHttpClient(): OkHttpClient {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier(HostnameVerifier { _, _ -> true })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }



    fun getPicassoUnsafeCertificate(context: Context): Picasso {
        val client = getUnsafeOkHttpClient()
        val picasso = Picasso.Builder(context).downloader( OkHttp3Downloader(client)).build()
        picasso.isLoggingEnabled = true
        return picasso
    }
//////////////////////////////////////////



    private fun subscribeToLiveData() {

        viewModel.liveData.observe(viewLifecycleOwner) {
            course = it
            Log.e("course", course.courseImage.toString())
            viewBinding.item = it
            var serverUrl = "25.70.83.232"


            /////////////////////////////
//                Glide.with(this).load("https://25.70.83.232:7097/Images/d5f8abc8-c567-43dc-a911-58327d69448a.jpeg")
////                .centerCrop()
//                .into(viewBinding.courseImageView)

            //////////////////////////// with unsafe - not tested yet
            getPicassoUnsafeCertificate(requireContext())
                .load("https://cdn-icons-png.flaticon.com/512/1256/1256397.png?w=360".toUri())
                .into(viewBinding.courseImageView)


            /////////////////////////// without unsafe - not tested yet
//            Picasso.get()
//                .load("https://cdn-icons-png.flaticon.com/512/1256/1256397.png?w=360".toUri())
////                .placeholder(R.drawable.ic_courses)
////                .error(com.google.android.material.R.drawable.mtrl_ic_error)
//                .into(viewBinding.courseImageView)


//            http://192.168.x.x/php/imagefolder/1234.jpg
//            var image=course.courseImage?.let { it1 -> TestConnection.getData(it1) }
//            val img= course.courseImage?.let { it1 -> File(it1) }
//            Log.e("image",img?.exists().toString())
        }

        viewModel.dropLiveData.observe(viewLifecycleOwner) {
            if (it.code() == 200) {
                Toast.makeText(requireContext(), "Course Dropped", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Course Not Dropped", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        viewBinding.item = course
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
        viewBinding.dropBtn.setOnClickListener {
            viewModel.dropCourse()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }


}


//@GlideModule
//class UnsafeOkHttpGlideModule : LibraryGlideModule() {
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        val client: OkHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient
//        registry.replace(
//            GlideUrl::class.java, InputStream::class.java,
//            OkHttpUrlLoader.Factory(client)
//        )
//    }
//}
//
//
//object UnsafeOkHttpClient {
//    // Create a trust manager that does not validate certificate chains
//    val unsafeOkHttpClient: OkHttpClient
//
//    // Install the all-trusting trust manager
//
//        // Create an ssl socket factory with our all-trusting manager
//        get() = try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
//                object : X509TrustManager {
//                    @Throws(CertificateException::class)
//                    override fun checkClientTrusted(
//                        chain: Array<X509Certificate?>?,
//                        authType: String?
//                    ) {
//                    }
//
//                    @Throws(CertificateException::class)
//                    override fun checkServerTrusted(
//                        chain: Array<X509Certificate?>?,
//                        authType: String?
//                    ) {
//                    }
//
//                    override fun getAcceptedIssuers(): Array<X509Certificate> {
//                        return arrayOf()
//                    }
//
//                    val acceptedIssuers: Array<X509Certificate>
//                        @JvmName("getAcceptedIssuers2")
//                        get() = arrayOf()
//                }
//            )
//
//            // Install the all-trusting trust manager
//            val sslContext: SSLContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, SecureRandom())
//
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
//            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
//            builder.sslSocketFactory(
//                sslSocketFactory,
//                trustAllCerts[0] as X509TrustManager
//            )
//            builder.hostnameVerifier { hostname, session -> true }
//            builder.build()
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//}


