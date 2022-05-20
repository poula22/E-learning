package com.example.lamp.ui.teacher.courses_page.course_content.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.database.DataBase
import com.example.extentions.clearTime
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseDashboardBinding
import com.example.lamp.ui.teacher.courses_page.TeacherCoursesFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment
import com.example.todo_app.AddTodoBottomSheet
import com.example.todo_app.TodoAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class TeacherCourseDashboardFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseDashboardBinding
    val adapter= TodoAdapter(null)
    lateinit var viewModel:TeacherCourseDashboardViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_dashboard,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=TeacherCourseDashboardViewModel(requireContext())
        initViews()
        subscirbeToLiveData()
        viewModel.getData()
        Log.v("data:::",viewModel.liveData.value.toString())
    }
    fun subscirbeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {

            Log.v(
                "poula: ",
                it.toString()
            )

        }
    }
    override fun onResume() {
        super.onResume()
        getTodosListFromDB()
    }
    var calendar= Calendar.getInstance()
    fun getTodosListFromDB() {
        val todoList= viewModel.repository.getTodoByDate(calendar.clearTime().time)
        adapter.changeData(todoList)
    }

    private fun initViews() {
        viewBinding.coursesNumberCard.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, TeacherCoursesFragment())
                .addToBackStack("")
                .commit()
        }

        viewBinding.studentsNumberCard.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, TeacherStudentsFragment())
                .addToBackStack("")
                .commit()
        }
        viewBinding.calendarView.selectedDate= CalendarDay.today()
        viewBinding.todoRecycler.adapter=adapter
        viewBinding.calendarView.setOnDateChangedListener{
                widget,calenderDay,selected->
            calendar.set(Calendar.DAY_OF_MONTH,calenderDay.day)
            calendar.set(Calendar.MONTH,calenderDay.month-1)
            calendar.set(Calendar.YEAR,calenderDay.year)
            getTodosListFromDB()
        }
        viewBinding.addBtn.setOnClickListener{
            showAddBottomSheet()
        }

    }
    private fun showAddBottomSheet() {
        val addTodoBottomSheet= AddTodoBottomSheet()
        addTodoBottomSheet.show(requireActivity().supportFragmentManager,"")
        addTodoBottomSheet.onTodoAddedListener=object :AddTodoBottomSheet.OnTodoAddedListener{
            override fun onTodoAdded() {
                    this@TeacherCourseDashboardFragment.getTodosListFromDB()
            }

        }
    }
}