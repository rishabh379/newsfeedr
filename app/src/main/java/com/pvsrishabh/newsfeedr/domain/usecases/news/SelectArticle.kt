package com.pvsrishabh.newsfeedr.domain.usecases.news

import com.pvsrishabh.newsfeedr.domain.model.Article
import com.pvsrishabh.newsfeedr.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}