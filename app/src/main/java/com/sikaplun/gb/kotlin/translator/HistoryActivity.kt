package com.sikaplun.gb.kotlin.translator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.translator.app.App
import com.sikaplun.gb.kotlin.translator.databinding.ActivityHistoryBinding
import com.sikaplun.gb.kotlin.translator.ui.adapters.HistoryActivityAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private val historyActivityAdapter by lazy { HistoryActivityAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWordHistoryRecyclerView()
        retrieveWords()
    }

    private fun initWordHistoryRecyclerView() {
        binding.apply {
            historyActivityRecyclerView.layoutManager = LinearLayoutManager(this@HistoryActivity)
            historyActivityRecyclerView.setHasFixedSize(true)
            historyActivityRecyclerView.adapter = historyActivityAdapter
        }
    }

    private fun retrieveWords() {
        lifecycleScope.launch(Dispatchers.IO) {
            val listWords = (applicationContext as App).repository.getAllDataModelLocal()

            withContext(Dispatchers.Main) {
                historyActivityAdapter.setWords(listWords)
            }
        }
    }
}