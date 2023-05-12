package com.example.lamp.ui.parent.parent_home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.ParentStudentRequestDTO
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentHomeBinding
import com.example.lamp.ui.parent.parent_home_page.children_recycler_view.ChildrenAdapter
import com.example.lamp.ui.parent.parent_home_page.courses_page.CoursesFragment
import com.example.lamp.ui.parent.parent_home_page.courses_page.parent_grades.StudentCourseGradesFragment
import java.util.regex.Pattern

class HomeFragment : Fragment() {
    lateinit var viewBinding: FragmentParentHomeBinding
    lateinit var adapter: ChildrenAdapter
    lateinit var viewModel: HomeViewModel
    lateinit var students: List<StudentResponseDTO>
    lateinit var names: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_parent_home, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getStudentsByParentId(CONSTANTS.user_id)
    }

    private fun subscribeToLiveData() {
        viewModel.liveDate.observe(viewLifecycleOwner) {

        }

        viewModel.parentLiveData.observe(viewLifecycleOwner) {
            adapter.changeData(it)
            viewBinding.noChildrenTxt.isVisible = it.isEmpty()
       }

    }

    private fun initViews() {

        adapter = ChildrenAdapter()
        if (adapter.itemCount == 0) {
            viewBinding.noChildrenTxt.visibility = View.VISIBLE
        } else {
            viewBinding.noChildrenTxt.visibility = View.GONE
        }
        var sentRequests = arrayListOf<String?>()
        var student =
            ParentStudentRequestDTO(viewBinding.email.editText?.text.toString(), CONSTANTS.user_id)
        viewBinding.addChildBtn.setOnClickListener {
            if(viewBinding.email.editText?.text.toString().isNotEmpty()){
            for (i in sentRequests) {
                if (i == student.studentEmail) {
                    Toast.makeText(context, "Pending student verification", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    sentRequests.add(student.studentEmail)
                    viewModel.verifyStudentRequest(student)
                }
            }
            }else if (!Pattern.matches(
                    "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                    viewBinding.email.editText?.text.toString()
                )
            ) {
                viewBinding.email.error = "Incorrect email address"
            } else {
                viewBinding.email.error = null
            }
        }


        adapter.onChildClickListener =
            object : ChildrenAdapter.OnChildClickListener {
                override fun setOnChildClickListener(child: StudentResponseDTO) {
                    val bundle= Bundle()
                    bundle.putInt("student_id",child.id!!)
                    val fragment = CoursesFragment()
                    fragment.arguments=bundle
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.parent_fragment_tab,
                            fragment
                        )
                        .commit()
                }
            }
        viewBinding.childrenRecyclerView.adapter = adapter
    }
}