package com.example.searching

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.example.searching.models.StudentModel
import com.example.searching.adapters.StudentAdapter
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val students = mutableListOf<StudentModel>()
        val firstNames = listOf("Nguyen", "Tran", "Le", "Pham", "Hoang")
        val lastNames = listOf("An", "Binh", "Chau", "Duy", "Hoa", "Linh", "Phong", "Quan", "Son", "Thao")

        for (i in 0..27) {
            val randomFirstName = firstNames.random()
            val randomLastName = lastNames.random()
            val randomMSSV = (100000 + Random.nextInt(900000)).toString() // Tạo MSSV ngẫu nhiên 6 chữ số

            students.add(StudentModel("$randomFirstName $randomLastName", randomMSSV))
        }

        val adapter = StudentAdapter(students)

        val listStudents = findViewById<ListView>(R.id.list_students)
        listStudents.adapter = adapter

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.length > 2) {
                    val filteredList = students.filter {
                        it.hoten.contains(query, ignoreCase = true) || it.mssv.contains(query)
                    }

                    adapter.clear()
                    adapter.addAll(filteredList)
                } else {

                    adapter.clear()
                    adapter.addAll(students)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}