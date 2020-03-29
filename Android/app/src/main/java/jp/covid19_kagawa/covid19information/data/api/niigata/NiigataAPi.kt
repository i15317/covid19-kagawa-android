package jp.covid19_kagawa.covid19information.data.api.niigata

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.niigata.NiigataData
import retrofit2.http.GET


interface NiigataAPi {

    // option 1: a resource relative to your base URL
    @GET("CodeForNiigata/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<NiigataData>

    @GET("https://raw.githubusercontent.com/CodeForNiigata/covid19/development/data/news.json")
    fun downloadNewsData(): Single<NewsData>

}