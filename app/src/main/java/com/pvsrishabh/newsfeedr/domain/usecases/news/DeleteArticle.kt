package com.pvsrishabh.newsfeedr.domain.usecases.news

import com.pvsrishabh.newsfeedr.domain.model.Article
import com.pvsrishabh.newsfeedr.domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}