package com.example.sample_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sample_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getAll().observe(this, Observer {
            binding.resultTextview.text = it.toString()
        })

        binding.addButton.setOnClickListener {
            CoroutineScope(Dispatchers .Main).launch {
                val temp: Deferred<Boolean> = async(Dispatchers.IO) {
                    viewModel.insertTodo(TodoEntity(binding.todoEdittext.text.toString()))
                    true
                }
                temp.await()
            }
        }
    }
}