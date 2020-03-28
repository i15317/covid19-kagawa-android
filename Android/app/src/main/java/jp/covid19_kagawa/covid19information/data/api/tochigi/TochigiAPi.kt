package jp.covid19_kagawa.covid19information.data.api.tochigi

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.iwate.IwateData
import jp.covid19_kagawa.covid19information.data.entity.tochigi.TochigiData
import retrofit2.http.GET


interface TochigiAPi {

    // option 1: a resource relative to your base URL
    @GET("covid19-tochigi/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<TochigiData>

    @GET("https://raw.githubusercontent.com/covid19-tochigi/covid19/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}