package com.example.lamp.ui.todo_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TodoDTO
import com.example.lamp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class TodoAdapter(var todoList: MutableList<TodoDTO>?) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val markAsDone: ImageView = view.findViewById(R.id.mark_as_done_button)
        val line: View = view.findViewById(R.id.line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = todoList?.get(position)
        holder.title.setText(item?.title)
        holder.description.setText(item?.description)
        if (item?.done == true) {
            holder.markAsDone.setBackgroundResource(R.drawable.rounded_btn_green)
            holder.line.setBackgroundColor(Color.parseColor("#00FF00"))
            holder.title.setTextColor(Color.parseColor("#3AA523"))
        }
        holder.markAsDone.setOnClickListener {
            item?.done = true
            val dialog =
                MaterialAlertDialogBuilder(holder.markAsDone.context)
                    .setTitle("Well Done!")
                    .setMessage("You have completed this task")
                    // Add customization options here
                    .show()
            //            val party = Party(
//                speed = 0f,
//                maxSpeed = 30f,
//                damping = 0.9f,
//                spread = 360,
//                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
//                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
//                position = Position.Relative(0.5, 0.3)
//            )
//            konfettiView.start(party)

        }
    }

    override fun getItemCount(): Int = todoList?.size ?: 0

    fun changeData(list: MutableList<TodoDTO>) {
        todoList = list
        notifyDataSetChanged()
    }
}