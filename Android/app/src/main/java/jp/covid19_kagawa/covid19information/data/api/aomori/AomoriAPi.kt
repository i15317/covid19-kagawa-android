package jp.covid19_kagawa.covid19information.data.api.aomori

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriData
import retrofit2.http.GET


interface AomoriAPi {

    // option 1: a resource relative to your base URL
    @GET("CodeForAomori/covid19/aomori_development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<AomoriData>

    @GET("https://raw.githubusercontent.com/CodeForAomori/covid19/aomori_development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}