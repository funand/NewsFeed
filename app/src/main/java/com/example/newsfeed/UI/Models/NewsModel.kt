package com.example.newsfeed.UI.Models

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Articles>
)
