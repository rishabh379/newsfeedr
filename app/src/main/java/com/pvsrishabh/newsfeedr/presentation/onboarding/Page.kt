package com.pvsrishabh.newsfeedr.presentation.onboarding

import androidx.annotation.DrawableRes
import com.pvsrishabh.newsfeedr.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Crypto Currency",
        description = "Get news related to Cryptocurrency",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Temples",
        description = "Get news related to Religious things",
        image = R.drawable.onboarding2
    ),Page(
        title = "Foods and Nutrition",
        description = "Get news related to Health and Lifestyle",
        image = R.drawable.onboarding3
    )
)