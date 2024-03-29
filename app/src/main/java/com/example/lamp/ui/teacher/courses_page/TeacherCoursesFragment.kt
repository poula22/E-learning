package com.example.lamp.ui.teacher.courses_page

import android.graphics.Bitmap
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
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.course_content.TeacherCourseDetails
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TeacherCoursesFragment : Fragment() {
    lateinit var teacherCoursesBinding: FragmentTeacherCoursesBinding
    lateinit var adapter: TeacherCoursesAdapter
    lateinit var viewModel: TeacherCoursesViewModel
    var mapOfCourses: HashMap<Int, Bitmap?> = hashMapOf()
    var i :MutableList<Int> = mutableListOf()
    var size=-1
    var counter=-1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherCoursesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_courses, container, false)
        return teacherCoursesBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCoursesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getAllCourses()
    }


    fun subscribeToLiveData() {
        viewModel.coursesLiveData.observe(viewLifecycleOwner){
            adapter.changeData(it)
            updateCoursesImages(it)
        }

        viewModel.deleteCourseLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Log.e("taaaaaa", it.toString())
                if (it.code()==200){
                    Toast.makeText(requireContext(), "courses deleted successfully", Toast.LENGTH_SHORT).show()
                    viewModel.getAllCourses()
                }else{
                    Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()
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
                    .let { it1 -> adapter.updateCoursesImages(it1) }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCourses()
    }


    private fun updateCoursesImages(it: MutableList<CourseResponseDTO>) {
        size=it.size
        counter=0
        it.forEach {
            it.courseImage?.let { it1 ->
                i.add(it.id!!)
                mapOfCourses.put(it.id!!,null)
//                viewModel.getImage(it1)
            }
        }

    }

    private fun initViews() {
        adapter = TeacherCoursesAdapter( type = 1)
        adapter.onCourseClickListener=object :TeacherCoursesAdapter.OnCourseClickListener{
            override fun setOnCourseClickListener(item: CourseResponseDTO?) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.teacher_fragment_tab,TeacherCourseDetails(item))
                    .commit()
                val floatingActionBtn: FloatingActionButton =requireActivity().findViewById(R.id.floating_action_btn)
                floatingActionBtn.isVisible=false
                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }

        }
        teacherCoursesBinding.teacherCoursesRecyclerView.adapter = adapter

        teacherCoursesBinding.floatingActionBtn.setOnClickListener {
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
                        val course  = adapter.coursesItemsList?.get(position)
                        Log.v("todo", course?.courseResponseDTO?.id.toString())
                        adapter.coursesItemsList?.removeAt(position)
                        viewModel.deleteCourse(course?.courseResponseDTO?.id!!)
                        adapter.notifyItemRemoved(position)
                    }
                    .setNegativeButton("No") { dialog, which ->
                        adapter.notifyItemChanged(viewHolder.absoluteAdapterPosition)
                    }
                    .show()

            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(teacherCoursesBinding.teacherCoursesRecyclerView)
    }

    private fun showAddBottomSheet() {
        val addCourseBottomSheet = TeacherAddCoursesBottomSheet()
        addCourseBottomSheet.show(requireActivity().supportFragmentManager, "")
        addCourseBottomSheet.onCourseAddedListener=object :TeacherAddCoursesBottomSheet.OnCourseAddedListener{
            override fun OnAddCourse() {
                viewModel.getAllCourses()
            }

        }
    }

}