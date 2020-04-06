package jp.covid19_kagawa.covid19information.data.api.aomori

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriCallDataItem
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriContactDataItem
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriData
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriInspectionDataItem
import retrofit2.http.GET


interface AomoriAPi {

    // option 1: a resource relative to your base URL
    @GET("CodeForAomori/covid19/aomori_production/data/data.json")
    fun downloadFileWithFixedUrl(): Single<AomoriData>

    @GET("https://raw.githubusercontent.com/CodeForAomori/covid19/aomori_production/data/news.json")
    fun downloadNewsData(): Single<NewsData>

    @GET("https://raw.githubusercontent.com/CodeForAomori/covid19/aomori_production/data/_inspection.json")
    fun fetchInspectionData(): Single<Array<AomoriInspectionDataItem>>

    @GET("https://raw.githubusercontent.com/CodeForAomori/covid19/aomori_production/data/_consult_call_center.json")
    fun fetchCallData(): Single<Array<AomoriCallDataItem>>

    @GET("https://raw.githubusercontent.com/CodeForAomori/covid19/aomori_production/data/_consult.json")
    fun fetchConsultData(): Single<Array<AomoriContactDataItem>>

}