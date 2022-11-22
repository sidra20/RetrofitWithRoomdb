package com.sidrakotlin.retrofitwithroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sidrakotlin.retrofitwithroom.databinding.PostsItemBinding
import com.sidrakotlin.retrofitwithroom.model.Posts
import com.sidrakotlin.retrofitwithroom.model.PostsItem

class PostsAdapter():RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {
    private val postList = ArrayList<PostsItem>()

    fun updateList(new:Posts){
        postList.clear()
        postList.addAll(new)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostsItemBinding.inflate(inflater,parent,false)
        val obj = MyViewHolder(binding)
        return obj
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val posts = postList[position]
        holder.binding.postId.text=posts.id.toString()
        holder.binding.title.text=posts.title
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class MyViewHolder(val binding: PostsItemBinding):RecyclerView.ViewHolder(binding.root)
    {

    }
}