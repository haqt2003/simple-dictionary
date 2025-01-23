package com.example.dictionary.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityDetailBinding
import com.example.dictionary.models.Word

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val word = intent.getParcelableExtra<Word>("word")
        if (word != null) {
            binding.tvHanDetails.text = word.han
            binding.tvVietDetails.text = word.viet
            binding.tvPinyinDetails.text = word.pinyin
            binding.tvMeanDetails.text = word.mean
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}