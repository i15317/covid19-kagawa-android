package jp.covid19_kagawa.covid19information2.data.api.iwate

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.iwate.IwateData
import retrofit2.http.GET


interface IwateAPi {

    // option 1: a resource relative to your base URL
    @GET("MeditationDuck/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<IwateData>

    @GET("https://raw.githubusercontent.com/MeditationDuck/covid19/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}