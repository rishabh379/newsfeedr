package com.pvsrishabh.newsfeedr.domain.usecases.news

import androidx.paging.PagingData
import com.pvsrishabh.newsfeedr.domain.model.Article
import com.pvsrishabh.newsfeedr.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources)
    }
}