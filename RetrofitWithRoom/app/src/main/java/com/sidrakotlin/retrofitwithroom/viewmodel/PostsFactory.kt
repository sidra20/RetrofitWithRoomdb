package com.sidrakotlin.retrofitwithroom.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sidrakotlin.retrofitwithroom.repository.PostsRepository

class PostsFactory(private val repository: PostsRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(repository)as T
    }
}