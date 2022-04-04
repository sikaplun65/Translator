package com.sikaplun.gb.kotlin.translator.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.translator.app.appComponent
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.translator.ui.adapter.MeaningWordAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MeaningWordAdapter

    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory

    private val viewModel: MainActivityViewModel by viewModels {viewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent.inject(this)

        initAdapter()
        initRecyclerView()
        initSearchButton()
        initQueryEditText()
        showMeaningsWord()

    }

    private fun showMeaningsWord() {
        viewModel.getMeaningWord().observe(this){
            if (it != null){
                adapter.setMeaningsWord(it as ArrayList<DataModel>)
                showLoading(false)
            }
        }

    }

    private fun initQueryEditText() {
        binding.queryEditText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchForWordMeanings()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun initSearchButton() {
        binding.searchButton.setOnClickListener {
            searchForWordMeanings()
        }
    }

    private fun searchForWordMeanings() {
        binding.apply {
            val query = queryEditText.text.toString()
            if (query.isEmpty()) {
                return
            }
            viewModel.findMeaningWord(query)
            showLoading(true)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun initRecyclerView() {
        binding.apply {
            foundWordMeaningsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            foundWordMeaningsRecyclerView.setHasFixedSize(true)
            foundWordMeaningsRecyclerView.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter() {
        adapter = MeaningWordAdapter()
        adapter.notifyDataSetChanged()
    }
}