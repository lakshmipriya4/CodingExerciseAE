package com.example.codingexerciseae

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingexerciseae.adapter.HiringListAdapter
import com.example.codingexerciseae.databinding.ActivityMainBinding
import com.example.codingexerciseae.viewmodel.HiringViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val hiringViewModel: HiringViewModel by viewModels()
    @Inject
    lateinit var hiringListAdapter: HiringListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        enableEdgeToEdge()
        setContentView(activityMainBinding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        activityMainBinding.recyclerView.adapter = hiringListAdapter
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        hiringViewModel.hiringList.observe(this) {
            hiringListAdapter.updateHiringList(it)
        }

        hiringViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}