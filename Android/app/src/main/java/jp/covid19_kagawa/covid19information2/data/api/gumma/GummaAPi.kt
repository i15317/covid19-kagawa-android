package jp.covid19_kagawa.covid19information2.data.api.gumma

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.gumma.GummaData
import retrofit2.http.GET


interface GummaAPi {

    // option 1: a resource relative to your base URL
    @GET("SatoshiRC/covid19-gunma/develop/data/data.json")
    fun downloadFileWithFixedUrl(): Single<GummaData>

    @GET("https://raw.githubusercontent.com/SatoshiRC/covid19-gunma/develop/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}