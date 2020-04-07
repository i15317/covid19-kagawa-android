package jp.covid19_kagawa.covid19information2.data.api.miyagi

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.miyagi.MiyagiData
import retrofit2.http.GET

interface MiyagiAPi {

    // option 1: a resource relative to your base URL
    @GET("code4shiogama/covid19-miyagi/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<MiyagiData>

    @GET("https://raw.githubusercontent.com/code4shiogama/covid19-miyagi/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}