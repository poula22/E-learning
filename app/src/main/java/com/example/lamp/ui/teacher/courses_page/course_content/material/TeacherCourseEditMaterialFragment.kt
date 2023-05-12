package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseEditMaterialBinding
import com.google.android.material.tabs.TabLayout

class TeacherCourseEditMaterialFragment:Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseEditMaterialBinding
    lateinit var viewModel: TeacherCourseEditMaterialViewModel
    lateinit var tabLayout: TabLayout
    lateinit var fragment: TeacherCourseAddLessonFragment
    lateinit var lesson:LessonResponseDTO
    lateinit var content:ContentResponseDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCourseEditMaterialViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_course_edit_material,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lesson=arguments?.getSerializable("lesson") as LessonResponseDTO
        subscribeToLiveData()
        initView()
        viewModel.getLessonContent(lesson.id!!)
    }

    private fun subscribeToLiveData(){
        viewModel.lessonLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "lesson updated successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
        viewModel.contentLiveData.observe(viewLifecycleOwner){
            content= it?.get(0)!!
        }
        viewModel.filesLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "content updated successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initView() {
        tabLayout = viewBinding.tabLayout
        val lesson = tabLayout.newTab()
        val content = tabLayout.newTab()
        initTabs(lesson, content)
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    Log.v("action;::",tab?.text.toString())
                    getSelectedTabContent(tab)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.v("action;::",tab?.text.toString())
                    getSelectedTabContent(tab)
                }
            }
        )
        tabLayout.selectTab(content)

        viewBinding.edit.setOnClickListener{
            if (tabLayout.selectedTabPosition == 0) {
                fragment.assignData()
                viewModel.updateContent(this.content.id!!,fragment.pdfFile,fragment.videofile,fragment.viewBinding.youtubeLink.text.toString())
            } else {
                viewModel.updateLesson(this.lesson.id!!,this.lesson)
            }
        }
    }
    private fun getSelectedTabContent(tab: TabLayout.Tab?) {

        if (tab?.text=="lesson") {
            viewBinding.edit.setText("Edit Lesson")
        }else if (tab?.text=="content"){
            viewBinding.edit.setText("Edit Content")
        }
        fragment = TeacherCourseAddLessonFragment()
        val bundle = Bundle()
        bundle.putString("tab", tab?.text.toString())
        fragment.arguments = bundle
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.material_edit_container, fragment)
            .commit()
    }

    private fun initTabs(
        lesson: TabLayout.Tab,
        content: TabLayout.Tab
    ) {
        lesson.text = "lesson"
        content.text = "content"
        tabLayout.addTab(content)
        tabLayout.addTab(lesson)
    }

}