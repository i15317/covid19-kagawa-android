package jp.covid19_kagawa.covid19information.data.api.kagawa

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.kagawa.KagawaData
import retrofit2.http.GET


interface KagawaAPi {

    // option 1: a resource relative to your base URL
    @GET("i15317/covid19/master/data/data.json")
    fun downloadFileWithFixedUrl(): Single<KagawaData>

    @GET("https://raw.githubusercontent.com/i15317/covid19/master/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}