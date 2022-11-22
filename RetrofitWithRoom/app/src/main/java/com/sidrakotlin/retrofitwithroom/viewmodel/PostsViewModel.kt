package com.sidrakotlin.retrofitwithroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidrakotlin.retrofitwithroom.AppDatabase
import com.sidrakotlin.retrofitwithroom.api.ApiInterface
import com.sidrakotlin.retrofitwithroom.api.PostsApi
import com.sidrakotlin.retrofitwithroom.model.Posts
import com.sidrakotlin.retrofitwithroom.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostsRepository):ViewModel() {


    init{
        viewModelScope.launch (Dispatchers.IO){
            repository.getPosts()
        }
    }
    val postsLiveData :LiveData<Posts>
    get() = repository.postsLv

}