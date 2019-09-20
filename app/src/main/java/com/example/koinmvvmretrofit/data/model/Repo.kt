package com.example.daggermvvmretrofit.data.model

import com.google.gson.annotations.SerializedName

class Repo(
        val id: Long,
        val name: String,
        val description: String,
        val owner: User,
        @SerializedName("stargazers_count")
        val stars: Long,
        @SerializedName("forks_count")
        val forks: Long
) {
        override fun toString(): String {
                return "Repo(id=$id, name='$name', description='$description', owner=$owner, stars=$stars, forks=$forks)"
        }
}