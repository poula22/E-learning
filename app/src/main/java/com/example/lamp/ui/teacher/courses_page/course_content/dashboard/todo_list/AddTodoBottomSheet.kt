package com.example.todo_app

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.data.database.DataBase
import com.example.data.model.entities.Todo
import com.example.extentions.clearTime
import com.example.lamp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class AddTodoBottomSheet:BottomSheetDialogFragment() {
    lateinit var tittleLayout:TextInputLayout
    lateinit var detailsLayout:TextInputLayout
    lateinit var addTodo:Button
    lateinit var chooseDate:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        tittleLayout=requireView().findViewById(R.id.tittle_layout)
        detailsLayout=requireView().findViewById(R.id.details_layout)
        addTodo=requireView().findViewById(R.id.add_todo_btn)
        chooseDate=requireView().findViewById(R.id.todo_choose_date)
        chooseDate.text = (""+calendar.get(Calendar.DAY_OF_MONTH).plus(1)+"/"+ calendar.get(Calendar.MONTH)+"/"
                + calendar.get(Calendar.YEAR))
        chooseDate.setOnClickListener{
            showDatePicker()
        }


        addTodo.setOnClickListener{
            if (validateForm()){
                val title=tittleLayout.editText?.text.toString()
                val description=detailsLayout.editText?.text.toString()
                val todo= Todo(title = title, description = description, date = calendar.clearTime().time,)
                Log.v("todo::",todo.title!!)
                DataBase.getInstance(requireContext().applicationContext).todoDao().addTodo(todo)
                onTodoAddedListener?.onTodoAdded()
                dismiss()
            }
        }
    }
    var onTodoAddedListener:OnTodoAddedListener?=null
    interface OnTodoAddedListener{
        fun onTodoAdded()
    }
    fun validateForm():Boolean{
        var isValid=true
        if(tittleLayout.editText?.text.toString().isBlank()){
            tittleLayout.error="please enter todo details"
            isValid=false
        }
        else{
            tittleLayout.error=null
        }
        if(detailsLayout.editText?.text.toString().isBlank()){
            detailsLayout.error="please enter todo details"
            isValid=false
        }
        else{
            detailsLayout.error=null
        }
        return isValid
    }
    val calendar=Calendar.getInstance()
    fun showDatePicker(){

        val datePicker=DatePickerDialog(requireContext(),
            { view, year, month, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.YEAR,year)
                chooseDate.setText(""+dayOfMonth+"/"+month.plus(1)+"/"+year)
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
}