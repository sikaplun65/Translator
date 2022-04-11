package com.sikaplun.gb.kotlin.translator.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sikaplun.gb.kotlin.translator.HistoryActivity
import com.sikaplun.gb.kotlin.translator.R
import com.sikaplun.gb.kotlin.translator.app.App
import com.sikaplun.gb.kotlin.translator.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.translator.ui.adapters.MeaningWordAdapter
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showMeaningsWord() {
        viewModel.getMeaningWord().observe(this) {
            if (it != null) {
                adapter.setMeaningsWord(it)
                showLoading(false)
            }

            val word = it[0].text.toString()
            val meaning = it[0].meanings?.get(0)?.translation?.translation.toString()
            val applicationContext = applicationContext as App

            viewModel.insertWordToDatabaseLocal(applicationContext, word, meaning)
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