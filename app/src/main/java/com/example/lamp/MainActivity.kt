package com.example.lamp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CONSTANTS
import com.example.lamp.ui.parent.ParentContainerFragment
import com.example.lamp.ui.sign_in_page.SigninFragment
import com.example.lamp.ui.sign_up_page.SignUpFragment
import com.github.dhaval2404.imagepicker.ImagePicker

class MainActivity : AppCompatActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CONSTANTS.IMAGE_REQUEST_CODE) {
                //Image Uri will not be null for RESULT_OK
                val selectedFile = data?.data //The uri with the location of the file
                val selectedFilePath = data?.data?.path
            } else if (requestCode == CONSTANTS.VOICE_REQUEST_CODE) {
                var result: ArrayList<String> =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushFragment(SigninFragment())
        val pref = getSharedPreferences("pref", MODE_PRIVATE);
        if (!pref.getBoolean("welcomed", false)) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            pref.edit().putBoolean("welcomed", true).apply()



        }
    }

    fun signUp(view: View) {
        pushFragment(SignUpFragment(), "add")
    }

    fun signIn(view: View) {
        pushFragment(ParentContainerFragment())
    }

    fun pushFragment(fragment: Fragment, string: String? = null) {
        var fragment = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (string != null) {
            fragment.addToBackStack("")
        }
        fragment.commit()
    }

}