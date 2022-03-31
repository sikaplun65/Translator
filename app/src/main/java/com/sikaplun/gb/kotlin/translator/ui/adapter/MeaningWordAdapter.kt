package com.sikaplun.gb.kotlin.translator.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.databinding.MeaningWordItemBinding

class MeaningWordAdapter : RecyclerView.Adapter<MeaningWordAdapter.MeaningWordViewHolder>() {

    private val data = ArrayList<DataModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMeaningsWord(data: ArrayList<DataModel>) {
        data.clear()
        data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningWordViewHolder {
        val view =
            MeaningWordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeaningWordViewHolder, position: Int) {
        holder.bind(data = data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MeaningWordViewHolder(private val binding: MeaningWordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            binding.apply {
                wordTextView.text = data.text
                meaningWordTextView.text = data.meanings?.get(0)?.translation?.translation
            }
        }
    }
}