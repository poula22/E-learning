package com.example.lamp.ui.student.student_home_page

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.common_functions.CONSTANTS
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        subscirbeToLiveData()
        initViews()
        viewModel.getData()
        viewModel.getCourses()
        viewModel.getImage()


    }

    fun subscirbeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            Log.v("poula: ", it.toString())
        }
        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            Log.v("poula: ", it.toString())
            if (it.size > 4) {
                coursesRVAdapter.changeData(it.subList(it.size - 4, it.size))
            } else {
                coursesRVAdapter.changeData(it)
            }

        }
        viewModel.testLiveData.observe(viewLifecycleOwner) {
            Log.v("poula: ", it.toString())
            viewBinding.roundedImageView.setImageBitmap(BitmapFactory.decodeStream(it.byteStream()))
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Log.v("poula: ", it.toString())
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.course.observe(viewLifecycleOwner) {
            Log.v("poula: ", it.toString())
            if (it?.code() == 200) {
                Toast.makeText(requireContext(), "courses added successfully", Toast.LENGTH_SHORT)
                    .show()
                viewModel.getCourses()
            } else {
                Toast.makeText(requireContext(), "invalid course code", Toast.LENGTH_SHORT).show()
            }
        }


        viewModel.parentLiveDateList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Parent verification")
                    .setMessage("Is this email your parent's email?" + "\n" + it[0].parentFirstName + " " + it[0].parentLastName)
                    .setNegativeButton("No") { dialog, which ->
                        viewModel.rejectParentRequest(it[0].parentId!!, CONSTANTS.user_id)
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, which ->
                        viewModel.acceptParentRequest(it[0].parentId!!, CONSTANTS.user_id)
                        dialog.dismiss()
                        Toast.makeText(
                            requireContext(),
                            "Now parent can follow your progress up",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    .show()
            }
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
        coursesRVAdapter = CoursesRVAdapter(type = 0)
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

        val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete this todo?")
                    .setPositiveButton("Yes") { dialog, which ->

                        val position = viewHolder.absoluteAdapterPosition
                        Log.v("position", position.toString())
                        val todo = adapter.todoList?.get(position)
                        Log.v("todo", todo?.id.toString())
                        adapter.todoList?.removeAt(position)
                        viewModel.repository.removeTodo(todo!!)
                        adapter.notifyItemRemoved(position)
                    }
                    .setNegativeButton("No") { dialog, which ->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()

            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(viewBinding.todoRecycler)

        viewBinding.joinCourseButton.setOnClickListener {
            viewModel.joinCourse(viewBinding.courseCode.text.toString().toInt())
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