package jp.covid19_kagawa.covid19information.data.api.gumma

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.gumma.GummaData
import retrofit2.http.GET


interface GummaAPi {

    // option 1: a resource relative to your base URL
    @GET("SatoshiRC/covid19-gunma/develop/data/data.json")
    fun downloadFileWithFixedUrl(): Single<GummaData>

    @GET("https://raw.githubusercontent.com/SatoshiRC/covid19-gunma/develop/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}