package com.example.lamp.ui.student.student_course_page.course_content.assignment

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentCourseAssignmentViewModel:ViewModel() {
    var liveData =MutableLiveData<MutableList<AssignmentItem>>()
    fun getData(bundle: Bundle){
        viewModelScope.launch {
            liveData.value=bundle.getSerializable("assignmentList") as MutableList<AssignmentItem>
        }
    }
    fun filterList(text: String):MutableList<AssignmentItem> {
        val filteredList = mutableListOf<AssignmentItem>()
        liveData.value?.let {
            for (item in it) {
                if (item.state.lowercase() == text.lowercase()) {
                    filteredList.add(item) //add to filtered list
                } else if(text.lowercase() == "all" ){
                    filteredList.add(item) // add all the items that are not filtered
                }
            }

        }
        return filteredList
    }
}