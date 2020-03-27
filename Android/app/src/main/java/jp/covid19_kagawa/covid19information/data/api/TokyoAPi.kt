package jp.covid19_kagawa.covid19information.data.api

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.tokyo.InfectData
import jp.covid19_kagawa.covid19information.data.entity.NewsData

import retrofit2.http.GET


interface TokyoAPi {

    // option 1: a resource relative to your base URL
    @GET("tokyo-metropolitan-gov/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<InfectData>

    @GET("https://raw.githubusercontent.com/tokyo-metropolitan-gov/covid19/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}