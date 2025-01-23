package com.example.dictionary.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityDetailBinding
import com.example.dictionary.datas.DictionaryDatabase
import com.example.dictionary.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val db = DictionaryDatabase.getInstance(this)
    private val noteDao = db.dictionaryDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getIntExtra("id", 0)
        lifecycleScope.launch(Dispatchers.IO) {
            val word = noteDao.findByID(id)
            withContext(Dispatchers.Main) {
                binding.tvHanDetails.text = word.han
                binding.tvVietDetails.text = word.viet
                binding.tvPinyinDetails.text = word.pinyin
                binding.tvMeanDetails.text = word.mean
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}