package com.example.lamp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aemerse.onboard.OnboardAdvanced
import com.aemerse.onboard.OnboardFragment


class OnBoardingActivity : OnboardAdvanced() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use OnboardFragment to use a pre-built fragment
        addSlide(
            OnboardFragment.newInstance(
                title = "Hey, Student!",
                description = "Your Platform to ease learning process and provide all Tools you need",
                resourceId = R.drawable.student_vector,

                titleTypefaceFontRes = R.font.robotoflex,
                descriptionTypefaceFontRes = R.font.robotoflex,
            )
        )

        addSlide(
            OnboardFragment.newInstance(
                title = "Hello, Teacher!",
                description = "Here are all you need to help you to manage your class",
                resourceId = R.drawable.teacher_vector,
                titleTypefaceFontRes = R.font.robotoflex,
                descriptionTypefaceFontRes = R.font.robotoflex,
            )
        )

        addSlide(
            OnboardFragment.newInstance(
                title = "Greetings, Parent!",
                description = "Follow up your children learning progress",
                resourceId = R.drawable.parent_vector,

                titleTypefaceFontRes = R.font.robotoflex,
                descriptionTypefaceFontRes = R.font.robotoflex,
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}