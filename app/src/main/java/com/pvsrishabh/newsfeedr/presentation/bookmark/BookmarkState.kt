package com.pvsrishabh.newsfeedr.presentation.bookmark

import com.pvsrishabh.newsfeedr.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
