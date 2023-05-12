package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.PdfViewerBinding
import java.io.InputStream
import java.net.URL


class PDFViewer:Fragment() {
    lateinit var viewBinding:PdfViewerBinding
    lateinit var viewModel: PDFViewerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PDFViewerViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.pdf_viewer,container,false)
        return viewBinding.root
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdf=requireArguments().getString("pdf")
        subscribeToLiveData()
        if (pdf != null) {
            viewModel.getFile(pdf)
        }
        //resources.openRawResource(R.raw.sheet_2)

    }

    private fun subscribeToLiveData() {
        viewModel.testLiveData.observe(viewLifecycleOwner){
            try {
                Thread{
                    viewBinding.pdfView.fromStream(it.byteStream()).load()
//                viewBinding.pdfView.fromStream(resources.openRawResource(R.raw.sheet_2)).load()
                }.run()

            }catch (e:Exception){
                Log.v("error of file",e.toString())
            }
        }
    }

}