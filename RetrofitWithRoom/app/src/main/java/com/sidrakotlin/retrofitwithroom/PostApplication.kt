package com.sidrakotlin.retrofitwithroom

import android.app.Application
import com.sidrakotlin.retrofitwithroom.api.PostsApi
import com.sidrakotlin.retrofitwithroom.repository.PostsRepository

class PostApplication:Application() {
    lateinit var repository: PostsRepository

    override fun onCreate() {
        super.onCreate()
        val apiInterface = PostsApi.retrofitService
        val db = AppDatabase.getDatabase(applicationContext)
        repository=PostsRepository(apiInterface,db,applicationContext)
    }
}