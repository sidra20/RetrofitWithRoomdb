package com.sidrakotlin.retrofitwithroom.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sidrakotlin.retrofitwithroom.AppDatabase
import com.sidrakotlin.retrofitwithroom.api.ApiInterface
import com.sidrakotlin.retrofitwithroom.model.Posts
import com.sidrakotlin.retrofitwithroom.model.PostsItem
import com.sidrakotlin.retrofitwithroom.utils.Utils

class PostsRepository(private val apiInterface:ApiInterface, private val db:AppDatabase, private val context: Context) {



    private val postsLiveData = MutableLiveData<Posts>()
    val postsLv:LiveData<Posts>
    get()=postsLiveData

    suspend fun getPosts()
    {

        if(Utils.isInternetAvail(context))
        {
            val result = apiInterface.getPosts()
            if(result.body()!=null)
            {
                postsLiveData.postValue(result.body())

                db.postsDao().insertPosts(result.body()!!)
            }
        }



//            Toast.makeText(context,"Internet is connected!",Toast.LENGTH_SHORT).show()


        else{
            val getPots = db.postsDao().getAllPosts()
            val postList = Posts()
            postList.addAll(getPots)
            postsLiveData.postValue(postList)

        }


    }
}