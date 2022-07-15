package com.example.lamp.ui.teacher.courses_page.course_content.material

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import java.io.Serializable

data class VideoItem(val youTubePlayer: YouTubePlayer, val path: String):Serializable
