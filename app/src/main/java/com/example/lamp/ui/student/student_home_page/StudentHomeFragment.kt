package com.example.lamp.ui.student.student_home_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.extentions.clearTime
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentHomeBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.ocr.OcrFragment
import com.example.lamp.ui.student.student_features_page.recitation.RecitationFragment
import com.example.lamp.ui.student.student_features_page.summarization.SummarizationFragment
import com.example.lamp.ui.student.student_features_page.translation.TranslationFragment
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeatureItem
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeaturesRVAdapter
import com.example.lamp.ui.todo_list.AddTodoBottomSheet
import com.example.lamp.ui.todo_list.TodoAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*


class StudentHomeFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentHomeBinding
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var featuresRVAdapter: FeaturesRVAdapter

    val adapter = TodoAdapter(null)
    lateinit var viewModel: StudentHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentHomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_home, container, false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscirbeToLiveData()
        viewModel.getData()
        Log.v("data:::", viewModel.liveData.value.toString())
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

    var calendar = Calendar.getInstance()
    fun getTodosListFromDB() {
        val todoList = viewModel.repository.getTodoByDate(calendar.clearTime().time)
        adapter.changeData(todoList)
    }


    private fun initViews() {
        coursesRVAdapter = CoursesRVAdapter(TestData.COURSES, type = 0)
        viewBinding.coursesRecyclerView.adapter = coursesRVAdapter
        featuresRVAdapter = FeaturesRVAdapter(TestData.FEATURES, type = 0)
        featuresRVAdapter.onFeatureClickListener = object : FeaturesRVAdapter.FeatureClickListener {
            override fun onFeatureClick(pos: Int, item: FeatureItem) {
                if (pos == 0) {
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, TranslationFragment()).commit()
                } else if (pos == 1) {
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, SummarizationFragment()).commit()
                } else if (pos == 2) {
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, RecitationFragment()).commit()
                } else if (pos == 3) {
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, OcrFragment()).commit()
                }
                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }

        }
        viewBinding.featureRecyclerView.adapter = featuresRVAdapter
        viewBinding.calendarView.selectedDate = CalendarDay.today()
        viewBinding.todoRecycler.adapter = adapter
        viewBinding.calendarView.setOnDateChangedListener { widget, calenderDay, selected ->
            calendar.set(Calendar.DAY_OF_MONTH, calenderDay.day)
            calendar.set(Calendar.MONTH, calenderDay.month - 1)
            calendar.set(Calendar.YEAR, calenderDay.year)
            getTodosListFromDB()
        }
        viewBinding.addBtn.setOnClickListener {
            showAddBottomSheet()
        }


    }

    private fun showAddBottomSheet() {
        var bundle = Bundle()
        bundle.putInt("type", 1)
        val addTodoBottomSheet = AddTodoBottomSheet()
        addTodoBottomSheet.arguments = bundle
        addTodoBottomSheet.show(requireActivity().supportFragmentManager, "")
        addTodoBottomSheet.onTodoAddedListener = object : AddTodoBottomSheet.OnTodoAddedListener {
            override fun onTodoAdded() {
                this@StudentHomeFragment.getTodosListFromDB()
            }
        }
    }
}