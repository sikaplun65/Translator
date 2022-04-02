package com.sikaplun.gb.kotlin.translator.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.translator.ui.adapter.MeaningWordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: MeaningWordAdapter by lazy { MeaningWordAdapter() }

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initSearchButton()
        initQueryEditText()
        showMeaningsWord()
    }

    private fun showMeaningsWord() {
        viewModel.getMeaningWord().observe(this) {
            if (it != null) {
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
                hideKeyboard(this, it)
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

    private fun hideKeyboard(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}