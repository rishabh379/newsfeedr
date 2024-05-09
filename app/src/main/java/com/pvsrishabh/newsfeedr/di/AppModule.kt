package com.pvsrishabh.newsfeedr.di

import android.app.Application
import androidx.room.Room
import com.pvsrishabh.newsfeedr.data.local.NewsDao
import com.pvsrishabh.newsfeedr.data.local.NewsDatabase
import com.pvsrishabh.newsfeedr.data.local.NewsTypeConvertor
import com.pvsrishabh.newsfeedr.data.manager.LocalUserManagerImpl
import com.pvsrishabh.newsfeedr.data.remote.NewsApi
import com.pvsrishabh.newsfeedr.data.repository.NewsRepositoryImpl
import com.pvsrishabh.newsfeedr.domain.manager.LocalUserManager
import com.pvsrishabh.newsfeedr.domain.repository.NewsRepository
import com.pvsrishabh.newsfeedr.domain.usecases.app_entry.AppEntryUseCases
import com.pvsrishabh.newsfeedr.domain.usecases.app_entry.ReadAppEntry
import com.pvsrishabh.newsfeedr.domain.usecases.app_entry.SaveAppEntry
import com.pvsrishabh.newsfeedr.domain.usecases.news.DeleteArticle
import com.pvsrishabh.newsfeedr.domain.usecases.news.GetNews
import com.pvsrishabh.newsfeedr.domain.usecases.news.NewsUseCases
import com.pvsrishabh.newsfeedr.domain.usecases.news.SearchNews
import com.pvsrishabh.newsfeedr.domain.usecases.news.SelectArticle
import com.pvsrishabh.newsfeedr.domain.usecases.news.SelectArticles
import com.pvsrishabh.newsfeedr.domain.usecases.news.UpsertArticle
import com.pvsrishabh.newsfeedr.util.Constants.BASE_URL
import com.pvsrishabh.newsfeedr.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME,
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}