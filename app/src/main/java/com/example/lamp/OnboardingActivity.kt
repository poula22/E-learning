package com.example.lamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xcode.onboarding.OnBoarder
import com.xcode.onboarding.OnBoardingPage


class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pages: MutableList<OnBoardingPage> = ArrayList()
        pages.add(
            OnBoardingPage(
                R.drawable.student_vector,
                "Hey, Student!",
                "Your Platform to ease learning process and provide all Tools you need"
            )
        )
        pages.add(
            OnBoardingPage(
                R.drawable.teacher_vector,
                "Hello, Teacher!",
                "Here are all you need to help you to manage your class"
            )
        )
        pages.add(
            OnBoardingPage(
                R.drawable.parent_vector,
                "Greetings, Parent!",
                "Follow up your children learning progress"
            )
        )
        OnBoarder.startOnBoarding(this, pages, null)
    }
}