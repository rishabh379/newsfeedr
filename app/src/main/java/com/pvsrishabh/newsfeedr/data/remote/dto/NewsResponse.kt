package com.pvsrishabh.newsfeedr.data.remote.dto

import com.pvsrishabh.newsfeedr.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)