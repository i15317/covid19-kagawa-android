package jp.covid19_kagawa.covid19information2.data.api.ibaraki

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.ibaraki.IbarakiData
import retrofit2.http.GET


interface IbarakiAPi {

    // option 1: a resource relative to your base URL
    @GET("a01sa01to/covid19-ibaraki/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<IbarakiData>

    @GET("https://raw.githubusercontent.com/a01sa01to/covid19-ibaraki/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}