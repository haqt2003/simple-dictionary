package com.example.dictionary.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.dictionary.R
import com.example.dictionary.adapters.DictionaryAdapter
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.datas.DictionaryDAO
import com.example.dictionary.datas.DictionaryDatabase
import com.example.dictionary.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), DictionaryAdapter.OnAdapterListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: DictionaryAdapter

    private val words: List<Word> = listOf()

    private lateinit var db: DictionaryDatabase
    private lateinit var noteDao: DictionaryDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = Room.databaseBuilder(
            applicationContext,
            DictionaryDatabase::class.java,
            "vt.db"
        ).createFromAsset("vt.db").build()
        noteDao = db.dictionaryDAO()

        adapter = DictionaryAdapter(words, this)

        with(binding) {
            rvDictionary.adapter = adapter
            rvDictionary.layoutManager = LinearLayoutManager(this@MainActivity)

            etSearch.addTextChangedListener {
                val searchQuery = binding.etSearch.text.toString()

                if (searchQuery.isNotEmpty()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val list = noteDao.findByString(searchQuery)
                        Log.d("OP", list.toString())
                        withContext(Dispatchers.Main) {
                            adapter.updateWords(list)
                            adapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    adapter.updateWords(words)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onClick(word: Word) {
        TODO("Not yet implemented")
    }
}