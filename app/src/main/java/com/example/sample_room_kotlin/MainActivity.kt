package com.example.sample_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.sample_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var db: TodoDatabase? = null

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TodoDatabase.getInstance(this)


        // livedata UI 갱신
        db!!.todoDao().getAll().observe(this, Observer {
            binding.resultTextview.text = it.toString()
        })

        binding.addButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val temp: Deferred<Boolean> = async(Dispatchers.IO) {
                    db!!.todoDao().insertTodo(TodoEntity(binding.todoEdittext.text.toString()))
                    true
                }
                temp.await()
            }
        }
    }
}