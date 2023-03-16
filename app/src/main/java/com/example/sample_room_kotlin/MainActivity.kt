package com.example.sample_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        CoroutineScope(Dispatchers.IO).launch { // 코루틴 사용 비동기로 실행
            binding.resultTextview.text = db!!.todoDao().getAll().toString()
        }

        binding.addButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val temp: Deferred<Boolean> = async(Dispatchers.IO) {
                    db!!.todoDao().insertTodo(TodoEntity(binding.todoEdittext.text.toString()))
                    binding.resultTextview.text = db!!.todoDao().getAll().toString()
                    true
                }
                temp.await()
            }
        }
    }
}