package com.example.lamp.ui.teacher.students_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherStudentsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.students_page.students_recycler_view.StudentItem
import com.example.lamp.ui.teacher.students_page.students_recycler_view.TeacherStudentsAdapter

class TeacherStudentsFragment(var itemList: MutableList<StudentItem>?=null) : Fragment() {
    lateinit var teacherStudentsBinding: FragmentTeacherStudentsBinding
    lateinit var adapter: TeacherStudentsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherStudentsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_students, container, false)
        return teacherStudentsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        adapter = TeacherStudentsAdapter(itemList)
        teacherStudentsBinding.teacherStudentsRecyclerView.adapter = adapter
        teacherStudentsBinding.studentsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
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

    fun filterList(text: String) {
        teacherStudentsBinding.teacherStudentsRecyclerView.visibility = View.VISIBLE
        teacherStudentsBinding.noStudentsFound.visibility = View.GONE
        if (text.isEmpty()){
            adapter.setFilteredList(itemList!!)
        }
        //filter list here
        var filteredList = mutableListOf<StudentItem>()
        itemList?.let {
            for (item in it) {
                if (item.studentName.lowercase().contains(text.lowercase())) {
                    filteredList.add(item)
                }
            }

        }

        if (filteredList.isEmpty()) {
            teacherStudentsBinding.teacherStudentsRecyclerView.visibility = View.GONE
            teacherStudentsBinding.noStudentsFound.visibility = View.VISIBLE
        } else {
            teacherStudentsBinding.teacherStudentsRecyclerView.adapter.let {
                if (it is TeacherStudentsAdapter) {
                    it.setFilteredList(filteredList)
                }
            }
        }
    }

}