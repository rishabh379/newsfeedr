package com.pvsrishabh.newsfeedr.presentation.search

import androidx.paging.PagingData
import com.pvsrishabh.newsfeedr.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)