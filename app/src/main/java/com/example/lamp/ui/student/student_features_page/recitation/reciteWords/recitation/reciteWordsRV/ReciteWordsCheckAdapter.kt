package com.example.lamp.ui.student.student_features_page.recitation.reciteWords.recitation.reciteWordsRV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemReciteWordCheckBinding
import com.example.lamp.ui.student.student_features_page.recitation.StudentWordRecitationBottomSheet
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet


class ReciteWordsCheckAdapter(var wordsList: List<ReciteWordsItem>?, var selector: Int) :
    RecyclerView.Adapter<ReciteWordsCheckAdapter.ViewHolder>() {
    class ViewHolder(var viewDataBinding: ItemReciteWordCheckBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemReciteWordCheckBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_student_recite_words, parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = wordsList?.get(position)
        holder.viewDataBinding.item = item
        if (selector == 1) {
            holder.viewDataBinding.arabicText.visibility = View.GONE
        } else if (selector == 2) {
            holder.viewDataBinding.englishText.visibility = View.GONE
        }

        if (position % 2 == 0) {
            holder.viewDataBinding.arabicText.setBackgroundResource(R.color.white)
            holder.viewDataBinding.englishText.setBackgroundResource(R.color.white)
            holder.viewDataBinding.recordIcn.setBackgroundResource(R.color.white)
        } else {
            holder.viewDataBinding.arabicText.setBackgroundResource(R.color.pink)
            holder.viewDataBinding.englishText.setBackgroundResource(R.color.pink)
            holder.viewDataBinding.recordIcn.setBackgroundResource(R.color.pink)
        }

//////////////////////
        holder.viewDataBinding.recordIcn.setOnClickListener { view ->
            val reciteWordBottomSheet = StudentWordRecitationBottomSheet()
            reciteWordBottomSheet.show(
                (view.context as AppCompatActivity).supportFragmentManager,
                "reciteWordBottomSheet"
            )
        }
//////////////////////


    }

    override fun getItemCount(): Int = wordsList?.size ?: 0

}