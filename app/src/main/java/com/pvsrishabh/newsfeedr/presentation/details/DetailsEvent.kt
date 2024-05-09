package com.pvsrishabh.newsfeedr.presentation.details

import com.pvsrishabh.newsfeedr.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()
}