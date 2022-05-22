package com.example.lamp.ui.teacher.courses_page.course_content.dashboard.todo_list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.entities.Todo
import com.example.domain.model.TodoDTO
import com.example.lamp.R

class TodoAdapter(var todoList: MutableList<TodoDTO>?):RecyclerView.Adapter<TodoAdapter.ViewHolder> (){
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title:TextView=view.findViewById(R.id.title)
        val description:TextView=view.findViewById(R.id.description)
        val markAsDone:ImageView=view.findViewById(R.id.mark_as_done_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_todo_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=todoList?.get(position)
        holder.title.setText(item?.title)
        holder.description.setText(item?.description)
    }

    override fun getItemCount(): Int =todoList?.size ?:0

    fun changeData(list:MutableList<TodoDTO>){
        todoList=list
        notifyDataSetChanged()
    }
}