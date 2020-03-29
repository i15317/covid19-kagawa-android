package jp.covid19_kagawa.covid19information.data.api.chiba

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.chiba.ChibaData
import retrofit2.http.GET


interface ChibaAPi {

    // option 1: a resource relative to your base URL
    @GET("codeforchiba/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<ChibaData>

    @GET("https://raw.githubusercontent.com/codeforchiba/covid19/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}