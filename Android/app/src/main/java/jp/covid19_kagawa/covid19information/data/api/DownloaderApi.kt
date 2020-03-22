package jp.covid19_kagawa.covid19information.data.api

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.InfectData

import retrofit2.http.GET


interface DownloaderApi {

    // option 1: a resource relative to your base URL
    @GET("tokyo-metropolitan-gov/covid19/development/data/data.json")
    fun downloadFileWithFixedUrl(): Single<InfectData>


}