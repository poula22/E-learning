package com.example.lamp.ui.student.student_course_page

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.common_functions.CONSTANTS
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCoursesBinding
import com.example.lamp.ui.student.student_course_page.course_content.StudentCourseDetails
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CoursesFragment : Fragment() {
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var studentCoursesBinding: FragmentStudentCoursesBinding
    lateinit var viewModel: CoursesViewModel
    var mapOfCourses: HashMap<Int, Bitmap?> = hashMapOf()
    var i :MutableList<Int> = mutableListOf()
    var size=-1
    var counter=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentCoursesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_courses, container, false)
        return studentCoursesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get student id from previous fragment
        initViews()
        subscribeToLiveData()
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModel.getAllCourses()
    }

    fun subscribeToLiveData() {
        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                updateCoursesList(it)
                updateCoursesImages(it)

            }

        }
        viewModel.deleteCourseLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Log.e("taaaaaa", it.toString())
                if (it.code()==200){
                    Toast.makeText(requireContext(), "courses deleted successfully", Toast.LENGTH_SHORT).show()
                    getAllCourses()
                }else{
                    Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.course.observe(viewLifecycleOwner) {
            Log.e("taaaaaa", it.toString())
            it?.let {
                if (it.code()==200){
                    Toast.makeText(requireContext(), "courses added successfully", Toast.LENGTH_SHORT).show()
                    getAllCourses()
                }else{
                    Toast.makeText(requireContext(), "invalid course code", Toast.LENGTH_SHORT).show()
                }
            }

        }

        viewModel.fileLiveData.observe(viewLifecycleOwner) {
            it?.toString()?.let { it1 -> Log.e("taaaaaa", it1) }
            it?.let {
                mapOfCourses.put(i.get(counter), BitmapFactory.decodeStream(it.byteStream()))
            }
            counter++
            if (counter < size) {
                mapOfCourses
                    .let { it1 -> coursesRVAdapter.updateCoursesImages(it1) }
            }
        }
    }

    private fun updateCoursesImages(it: MutableList<CourseResponseDTO>) {
        size=it.size
        Log.e("taaaaaa", size.toString())
        counter=0
        it.forEach {
            it.courseImage?.let { it1 ->
                i.add(it.id!!)
                mapOfCourses.put(it.id!!,null)
                viewModel.getImage(it1)
            }
        }

    }

    private fun updateCoursesList(coursesList: MutableList<CourseResponseDTO>) {
        coursesRVAdapter.updateCoursesList(coursesList)
    }

    private fun initViews() {
        coursesRVAdapter = CoursesRVAdapter( type = 1)


        coursesRVAdapter.onCourseClickListener = object : CoursesRVAdapter.OnCourseClickListener {
            override fun setOnCourseClickListener(item: CourseResponseDTO?) {
                goToCourseDetails(item)
            }
        }
        studentCoursesBinding.studentCoursesRecyclerView.adapter = coursesRVAdapter
        studentCoursesBinding.joinCourseButton.setOnClickListener {
            joinCourse()
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
                        val course  = coursesRVAdapter.coursesItemsList?.get(position)
                        Log.v("todo", course?.courseResponseDTO?.id.toString())
                        coursesRVAdapter.coursesItemsList?.removeAt(position)
                        viewModel.deleteCourse(course?.courseResponseDTO?.id!!)
                        coursesRVAdapter.notifyItemRemoved(position)
                    }
                    .setNegativeButton("No") { dialog, which ->
                        coursesRVAdapter.notifyItemChanged(viewHolder.absoluteAdapterPosition)
                    }
                    .show()

            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(studentCoursesBinding.studentCoursesRecyclerView)
    }

    private fun goToCourseDetails(item: CourseResponseDTO?) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.student_fragment_tab, StudentCourseDetails(item))
            .commit()
//                viewModel.deleteCourse(item?.id!!)
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = false
    }

    private fun joinCourse() {
        var courseCode: String? = null
        val joinCourse = EditText(requireContext())
        joinCourse.hint = "Enter course code"
        joinCourse.inputType = TYPE_CLASS_NUMBER
        MaterialAlertDialogBuilder(requireContext())
            // Add customization options here
            .setTitle("Join Course")
            .setView(joinCourse)
            .setPositiveButton("Join") { dialog, which ->
                courseCode = joinCourse.text.toString()
                if (courseCode.isNullOrEmpty()) {
                    joinCourse.error = "Please enter course code"
                } else {
                    courseCode?.let { Integer.parseInt(it) }?.let {
                        viewModel.joinCourse(CONSTANTS.user_id,
                            it
                        )
                    }
                }
            }
            .show()
    }


}