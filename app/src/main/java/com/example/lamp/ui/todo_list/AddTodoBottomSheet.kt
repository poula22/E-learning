package com.example.lamp.ui.todo_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.data.database.DataBase
import com.example.data.model.entities.StudentTodo
import com.example.data.model.entities.TeacherTodo
import com.example.extentions.clearTime
import com.example.lamp.R
import com.example.lamp.databinding.FragmentAddTodoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddTodoBottomSheet : BottomSheetDialogFragment() {
    lateinit var viewBinding: FragmentAddTodoBinding
    var type:Int?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_todo, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        type=requireArguments().getInt("type")
        Log.v("Type:::::",type.toString())
        initViews()
    }

    fun initViews() {
        viewBinding.todoChooseDate.text = ("" + calendar.get(Calendar.DAY_OF_MONTH)
                + "/" + calendar.get(Calendar.MONTH).plus(1) + "/"
                + calendar.get(Calendar.YEAR))
        viewBinding.todoChooseDate.setOnClickListener {
            showDatePicker()
        }


        viewBinding.addTodoBtn.setOnClickListener {
            if (validateForm()) {
                val title = viewBinding.tittleLayout.editText?.text.toString()
                val description = viewBinding.detailsLayout.editText?.text.toString()

                if (type==0){
                    val teacherTodo = TeacherTodo(
                        title = title,
                        description = description,
                        date = calendar.clearTime().time,
                    )
                    Log.v("todo::", teacherTodo.title!!)
                    DataBase.getInstance().teacherTodoDao().addTodo(teacherTodo)
                }
                else{
                    val studentTodo = StudentTodo(
                        title = title,
                        description = description,
                        date = calendar.clearTime().time,
                    )
                    Log.v("todo::", studentTodo.title!!)
                    DataBase.getInstance().studentTodoDao().addTodo(studentTodo)
                }
                onTodoAddedListener?.onTodoAdded()
                dismiss()
            }
        }
    }

    var onTodoAddedListener: OnTodoAddedListener? = null

    interface OnTodoAddedListener {
        fun onTodoAdded()
    }

    fun validateForm(): Boolean {
        var isValid = true
        if (viewBinding.tittleLayout.editText?.text.toString().isBlank()) {
            viewBinding.tittleLayout.error = "please enter todo details"
            isValid = false
        } else {
            viewBinding.tittleLayout.error = null
        }
        if (viewBinding.detailsLayout.editText?.text.toString().isBlank()) {
            viewBinding.detailsLayout.error = "please enter todo details"
            isValid = false
        } else {
            viewBinding.detailsLayout.error = null
        }
        return isValid
    }

    val calendar = Calendar.getInstance()
    fun showDatePicker() {

        val datePicker = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                viewBinding.todoChooseDate.setText("" + dayOfMonth + "/" + month.plus(1) + "/" + year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}