package com.example.koinmvvmretrofit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.koinmvvmretrofit.data.model.Repo
import com.example.koinmvvmretrofit.BR
import com.example.koinmvvmretrofit.R
import com.example.koinmvvmretrofit.databinding.ItemListPostBinding

class ListPostAdapter : RecyclerView.Adapter<ListPostAdapter.ListViewHolder>(){
    val listPost = ArrayList<Repo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_post,parent,false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding?.setVariable(BR.strForks, listPost[position].forks.toString())
        holder.binding?.setVariable(BR.strStars, listPost[position].stars.toString())
        holder.binding?.setVariable(BR.strRepoName, listPost[position].name)
        holder.binding?.setVariable(BR.strDescription, listPost[position].description)
    }

    class ListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val binding = DataBindingUtil.bind<ItemListPostBinding>(itemView)
    }

    fun addPostData(listPost : List<Repo>){
        this.listPost.addAll(listPost)
        notifyDataSetChanged()
    }

}