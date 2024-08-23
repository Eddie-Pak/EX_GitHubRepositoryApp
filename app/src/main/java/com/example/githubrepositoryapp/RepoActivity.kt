package com.example.githubrepositoryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubrepositoryapp.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}