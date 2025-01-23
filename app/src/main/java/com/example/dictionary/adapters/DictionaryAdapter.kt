package com.example.dictionary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.databinding.LayoutItemBinding
import com.example.dictionary.models.Word

class DictionaryAdapter(
    private var words: List<Word>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<DictionaryAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)

        holder.itemView.setOnClickListener {
            listener.onClick(word)
        }
    }

    override fun getItemCount(): Int {
        return words.size
    }

    fun updateWords(newWords: List<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(word: Word)
    }

    class NoteViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.tvViet.text = word.viet
            binding.tvPinyin.text = word.pinyin
            binding.tvHan.text = word.han
        }
    }
}