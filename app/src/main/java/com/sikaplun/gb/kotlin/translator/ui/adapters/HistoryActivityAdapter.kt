package com.sikaplun.gb.kotlin.translator.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.translator.databinding.HistoryItemBinding
import com.sikaplun.gb.kotlin.translator.room.DataModelLocal

class HistoryActivityAdapter :
    RecyclerView.Adapter<HistoryActivityAdapter.HistoryItemViewHolder>() {

    private val wordsList = mutableListOf<DataModelLocal>()

    @SuppressLint("NotifyDataSetChanged")
    fun setWords(words: List<DataModelLocal>) {
        wordsList.clear()
        wordsList.addAll(words)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.bind(data = wordsList[position])
    }

    override fun getItemCount() = wordsList.size

    inner class HistoryItemViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModelLocal) {
            binding.apply {
                wordHistoryTextView.text = data.word
                meaningWordTextView.text = data.description
            }
        }

    }
}