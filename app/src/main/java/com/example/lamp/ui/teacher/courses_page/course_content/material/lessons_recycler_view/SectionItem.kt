package com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view

import java.util.*

data class SectionItem(
    var youtubeLink: String?,
    var videoPath: String?,
    var attachmentPath: String?,
    var paragraph: String?,
    var sectionName: String,
    var publishAt: String? = Calendar.getInstance().time.toString()
)