package com.example.lamp.ui.teacher.courses_page.course_content.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.databinding.FragmentTeacherCourseStudentsBinding
import com.example.lamp.ui.teacher.students_page.students_recycler_view.StudentItem
import com.example.lamp.ui.teacher.students_page.students_recycler_view.TeacherStudentsAdapter

class TeacherCourseStudentsFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseStudentsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_teacher_course_students,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        viewBinding.studentsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText!!)
                return true
            }
        })
    }

    lateinit var itemList: MutableList<StudentItem>
    val adapter = TeacherStudentsAdapter(itemList)

    fun filterList(text: String) {
        //filter list here
        var filteredList = mutableListOf<StudentItem>()
        for (item in itemList) {
            if (item.studentName.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }

        if (filteredList.isEmpty()) {
            viewBinding.teacherStudentsRecyclerView.visibility = View.GONE
            viewBinding.noStudentsFound.visibility = View.VISIBLE
        } else {
            viewBinding.teacherStudentsRecyclerView.adapter.let {
                if (it is TeacherStudentsAdapter) {
                    it.setFilteredList(filteredList)
                }
            }
        }
    }

}