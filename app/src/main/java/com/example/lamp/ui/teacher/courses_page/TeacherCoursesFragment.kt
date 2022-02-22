package com.example.lamp.ui.teacher.courses_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter

class TeacherCoursesFragment:Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:TeacherCoursesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_courses,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.teacher_courses_recycler_view)
        adapter=TeacherCoursesAdapter()
        recyclerView.adapter=adapter
    }

}