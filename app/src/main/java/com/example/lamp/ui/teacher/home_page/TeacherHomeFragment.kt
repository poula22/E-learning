package com.example.lamp.ui.teacher.home_page

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.extentions.clearTime
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherHomeBinding
import com.example.lamp.ui.teacher.courses_page.TeacherCoursesFragment
import com.example.lamp.ui.teacher.profile_page.TeacherProfileFragment
import com.example.lamp.ui.teacher.courses_page.course_content.students.TeacherStudentsFragment
import com.example.lamp.ui.todo_list.AddTodoBottomSheet
import com.example.lamp.ui.todo_list.TodoAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class TeacherHomeFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherHomeBinding

    //    lateinit var adapter: TeacherCoursesAdapter
    lateinit var viewModel: TeacherHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherHomeViewModel::class.java)
    }

    val adapter = TodoAdapter(null)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate<FragmentTeacherHomeBinding>(
            inflater,
            R.layout.fragment_teacher_home,
            container,
            false
        )
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(viewBinding.todoRecycler)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscirbeToLiveData()
        viewModel.getCourses()
        viewModel.getData()
        viewModel.getImage()
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
            adapter.changeData(it)
        }

        viewModel.removeLiveData.observe(
            viewLifecycleOwner
        ) {
            Log.v(
                "poula: ","removed"
            )
        }
        viewModel.coursesLiveData.observe(viewLifecycleOwner){
            Log.v("poula: ",it.toString())
            viewBinding.coursesCode.setText(it.size.toString())
        }

        viewModel.testLiveData.observe(viewLifecycleOwner){
            Log.v("poula: ",it.toString())
            viewBinding.roundedImageView.setImageBitmap(BitmapFactory.decodeStream(it.byteStream()))
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Log.v("poula: ",it.toString())
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        getTodosListFromDB()
    }

    var calendar = Calendar.getInstance()

    fun getTodosListFromDB() {
        viewModel.getTodoByDate(calendar.clearTime().time)

    }

    private fun initViews() {


        viewBinding.roundedProfile.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.teacher_fragment_tab, TeacherProfileFragment())
                .commit()
        }
        viewBinding.coursesNumberCard.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, TeacherCoursesFragment())
                .addToBackStack("")
                .commit()
        }


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
        bundle.putInt("type", 0)
        val addTodoBottomSheet = AddTodoBottomSheet()
        addTodoBottomSheet.arguments = bundle
        addTodoBottomSheet.show(requireActivity().supportFragmentManager, "")
        addTodoBottomSheet.onTodoAddedListener = object : AddTodoBottomSheet.OnTodoAddedListener {
            override fun onTodoAdded() {
                this@TeacherHomeFragment.getTodosListFromDB()
            }
        }
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
                    val todo = adapter.todoList?.get(position)
                    adapter.todoList?.removeAt(position)
                    viewModel.removeTodo(todo!!)
                    adapter.notifyItemRemoved(position)
                }
                .setNegativeButton("No") { dialog, which ->
                    adapter.notifyItemChanged(viewHolder.absoluteAdapterPosition)
                }
                .show()

        }
    }


}