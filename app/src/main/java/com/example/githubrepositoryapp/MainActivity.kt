package com.example.githubrepositoryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositoryapp.adapter.UserAdapter
import com.example.githubrepositoryapp.databinding.ActivityMainBinding
import com.example.githubrepositoryapp.model.Repo
import com.example.githubrepositoryapp.model.UserDto
import com.example.githubrepositoryapp.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val retrofit =Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        githubService.listRepos("square").enqueue(object: Callback<List<Repo>> {
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                Log.e("MainActivity", "List Repo: ${response.body().toString()}")
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//
//            }
//
//        })

        userAdapter = UserAdapter()

        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        binding.searchEditText.addTextChangedListener {
            searchUser(it.toString())
        }

    }

    private fun searchUser(query: String) {
        val githubService = retrofit.create(GithubService::class.java)
        githubService.searchUsers(query).enqueue(object: Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("MainActivity", "Search User: ${response.body().toString()}")

                userAdapter.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}