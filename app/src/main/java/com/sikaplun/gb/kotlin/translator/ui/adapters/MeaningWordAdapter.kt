package com.sikaplun.gb.kotlin.translator.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.databinding.MeaningWordItemBinding

class MeaningWordAdapter : RecyclerView.Adapter<MeaningWordAdapter.MeaningWordViewHolder>() {

    private val listWordMeanings = mutableListOf<DataModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMeaningsWord(data: List<DataModel>) {
        listWordMeanings.clear()
        listWordMeanings.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningWordViewHolder {
        val view =
            MeaningWordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeaningWordViewHolder, position: Int) {
        holder.bind(data = listWordMeanings[position])
    }

    override fun getItemCount(): Int = listWordMeanings.size

    inner class MeaningWordViewHolder(private val binding: MeaningWordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.meanings?.get(0)?.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(descriptionImageview)

                wordTextView.text = data.text
                meaningWordTextView.text = data.meanings?.get(0)?.translation?.translation
            }
        }
    }
}