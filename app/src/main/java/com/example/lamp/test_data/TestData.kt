package com.example.lamp.test_data

import com.example.extentions.clearTime
import com.example.lamp.R
import com.example.lamp.ui.parent.parent_communicate_page.communicate_recycler_view.TeacherItem
import com.example.lamp.ui.parent.parent_courses_page.course_recycler_view.ParentCourseItem
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentFromStudentItem
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.reciteWordsRV.ReciteWordsItem
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeatureItem
import com.example.lamp.ui.teacher.students_page.students_recycler_view.StudentItem
import java.util.*


object TestData {
    val TRANSLATIONAPIKEY = "fdacb2ce0bmshb0a36e2081822c8p1e7ae6jsn82387bc6a932"
    var COURSES: MutableList<CourseItem> = initCourses()
    val FEATURES: MutableList<FeatureItem> = mutableListOf(
        FeatureItem("translate", R.drawable.ic_translate),
        FeatureItem("summary", R.drawable.ic_summary),
        FeatureItem("Recite", R.drawable.ic_edit_text),
        FeatureItem("OCR", R.drawable.ic_image)
    )
    val WEBSITES: MutableList<String> = mutableListOf(
        "https://github.com/arhanashik/LinkPreview",
        "https://github.com/arhanashik/LinkPreview",
        "https://pay.google.com/gp/w/u/2/home/subscriptionsandservices"
    )
    val TOOLS: MutableList<String> = mutableListOf(
        "whatsApp", "youtube", "facebook"
    )

    var STUDENTS: MutableList<StudentItem> = initStudents()
    var TEACHERS: MutableList<TeacherItem> = initTeachers()
    val CHILDREN: MutableList<String> = mutableListOf(
        "child 1",
        "child 2",
        "child 3"
    )
    var PARENTCOURSES: MutableList<ParentCourseItem> = initParentCourse()

    val RECITATIONWORD: MutableList<ReciteWordsItem> =
        mutableListOf(ReciteWordsItem("عربي", "english"))
    val WORDs: MutableList<ReciteWordsItem> = mutableListOf(ReciteWordsItem("عربي", "english"))

    var ASSIGNMENTS: MutableList<AssignmentItem> = initAssignments()

    private fun initAssignments(): MutableList<AssignmentItem> {
        ASSIGNMENTS = mutableListOf()
        for (i in 1..50) {
            ASSIGNMENTS.add(
                AssignmentItem(
                    "assignment$i",
                    "",
                    Calendar.getInstance().clearTime().time,
                    Calendar.getInstance().clearTime().time,
                    "submitted",
                    100, mutableListOf()
                )
            )
        }
        for (i in 51..100) {
            ASSIGNMENTS.add(
                AssignmentItem(
                    "assignment$i",
                    "",
                    Calendar.getInstance().time,
                    Calendar.getInstance().clearTime().time,
                    "not submitted",
                    100, mutableListOf()
                )
            )
        }
        return ASSIGNMENTS
    }


    var ASSIGNMENT_FROM_STUDENT: MutableList<AssignmentFromStudentItem?>? = initAssignmentsFromStudents()

    private fun initAssignmentsFromStudents(): MutableList<AssignmentFromStudentItem?>? {
        ASSIGNMENT_FROM_STUDENT = mutableListOf()
        for (i in 1..50) {
            ASSIGNMENT_FROM_STUDENT!!.add(
                AssignmentFromStudentItem("student$i", "", "", "", 30,30)
            )
        }
        for (i in 51..100) {
            ASSIGNMENT_FROM_STUDENT!!.add(
                AssignmentFromStudentItem("student$i", "", "", "", 30,30)
            )
        }
        return ASSIGNMENT_FROM_STUDENT
    }


    private fun initParentCourse(): MutableList<ParentCourseItem> {
        PARENTCOURSES = mutableListOf()
        for (i in 1..100) {
            PARENTCOURSES.add(
                ParentCourseItem("child " + i, "course " + i, "teacher")
            )
        }
        return PARENTCOURSES
    }

    private fun initTeachers(): MutableList<TeacherItem> {
        TEACHERS = mutableListOf()
        for (i in 1..100) {
            TEACHERS.add(
                TeacherItem("name " + i, "01000" + i, "name" + i + "@gmail.com", "name " + i)
            )
        }
        return TEACHERS
    }

    private fun initStudents(): MutableList<StudentItem> {
        STUDENTS = mutableListOf()
        for (i in 1..100) {
            STUDENTS.add(
                StudentItem(
                    "name " + i, R.drawable.ic_profile, "sdfdsfsdfsd", "sdfdsfsdfdsf", "sdfsdfsdfs"
                )
            )
        }
        return STUDENTS
    }

    private fun initCourses(): MutableList<CourseItem> {
        COURSES = mutableListOf()
        for (i in 1..100) {
            COURSES.add(
                CourseItem(
                    "course " + i,
                    "teacher " + i,
                    "description " + i,
                    "JD3K8" + (1000 + i),
                    R.drawable.login,
                    "start",
                    "end"
                )
            )
        }
        return COURSES
    }

}