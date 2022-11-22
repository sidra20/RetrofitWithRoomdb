package com.sidrakotlin.retrofitwithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidrakotlin.retrofitwithroom.adapter.PostsAdapter
import com.sidrakotlin.retrofitwithroom.api.ApiInterface
import com.sidrakotlin.retrofitwithroom.api.PostsApi
import com.sidrakotlin.retrofitwithroom.databinding.ActivityMainBinding
import com.sidrakotlin.retrofitwithroom.repository.PostsRepository
import com.sidrakotlin.retrofitwithroom.utils.Utils
import com.sidrakotlin.retrofitwithroom.viewmodel.PostsFactory
import com.sidrakotlin.retrofitwithroom.viewmodel.PostsViewModel
import okio.Utf8

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostsViewModel
    private lateinit var adapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: PostsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val db = AppDatabase.getDatabase(this)
        val apiInterface=PostsApi.retrofitService
        repository=PostsRepository(apiInterface,db,this)

        viewModel= ViewModelProvider(this,PostsFactory(repository)).get(PostsViewModel::class.java)
        binding.recyclerPosts.layoutManager= LinearLayoutManager(this)

        adapter= PostsAdapter()
        binding.recyclerPosts.adapter=adapter

        viewModel.postsLiveData.observe(this,{

            //binding.progress.visibility= View.GONE

            adapter.updateList(it)
            Log.d("Hello", "onCreate: ${it} \n")
            Toast.makeText(this,"Size: "+it.size,Toast.LENGTH_SHORT).show()

        })
        if(Utils.isInternetAvail(this))
        {
            Toast.makeText(this,"Internet is connected!",Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(this,"Internet is not available!",Toast.LENGTH_SHORT).show()
        }
    }
}