package com.sidrakotlin.retrofitwithroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sidrakotlin.retrofitwithroom.model.PostsItem

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post:List<PostsItem>)

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts():List<PostsItem>
}