package com.pvsrishabh.newsfeedr.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pvsrishabh.newsfeedr.R
import com.pvsrishabh.newsfeedr.domain.model.Article
import com.pvsrishabh.newsfeedr.domain.model.Source
import com.pvsrishabh.newsfeedr.presentation.Dimens.ArticleCardSize
import com.pvsrishabh.newsfeedr.presentation.Dimens.ExtraSmallPadding
import com.pvsrishabh.newsfeedr.presentation.Dimens.ExtraSmallPadding2
import com.pvsrishabh.newsfeedr.presentation.Dimens.SmallIconSize
import com.pvsrishabh.newsfeedr.ui.theme.NewsFeedrTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .padding(end = ExtraSmallPadding2),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .height(ArticleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    NewsFeedrTheme {
        ArticleCard(
            article = Article(
                source = Source("", ""),
                author = "Andy Greenberg",
                title = "A Vast New Dataset Could Supercharge the AI Hunt for Crypto Money Laundering",
                description = "Blockchain analysis firm Elliptic, MIT, and IBM, have released a new AI detection model—and the 200-million-transaction dataset it's trained on—that aims to spot the “shape” of Bitcoin money laundering.",
                url = "https://www.wired.com/story/ai-crypto-tracing-model-money-laundering/",
                urlToImage = "https://media.wired.com/photos/6631a1936dc0c77846852ed5/191:100/w_1280,c_limit/Crypto-Money-Laundering-Security-GettyImages-1543076825.jpg",
                publishedAt = "2024-05-01T13:00:00Z",
                content = "As a test of their resulting AI tool, the researchers checked its outputs with one cryptocurrency exchangewhich the paper doesn't nameidentifying 52 suspicious chains of transactions that had all ult… [+3279 chars]"
            )
        ) {

        }
    }
}