package com.example.searching.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.searching.R
import com.example.searching.models.StudentModel
class StudentAdapter(val students: List<StudentModel>): BaseAdapter() {
    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_student_item, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        val student = students[position]
        viewHolder.textHoten.text = student.hoten
        viewHolder.textMssv.text = student.mssv


        return itemView
    }

    class ViewHolder(itemView: View) {
        val textHoten: TextView
        val textMssv: TextView
        init {
            textHoten = itemView.findViewById(R.id.text_hoten)
            textMssv = itemView.findViewById(R.id.text_mssv)
        }
    }

    fun clear() {
        students.clear()
        notifyDataSetChanged()
    }

    // Thêm phương thức addAll() để thêm danh sách sinh viên mới
    fun addAll(newStudents: List<StudentModel>) {
        students.addAll(newStudents)
        notifyDataSetChanged()
    }
}