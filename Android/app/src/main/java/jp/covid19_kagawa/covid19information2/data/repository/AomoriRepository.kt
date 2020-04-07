package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.aomori.AomoriAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.aomori.AomoriCallDataItem
import jp.covid19_kagawa.covid19information2.data.entity.aomori.AomoriContactDataItem
import jp.covid19_kagawa.covid19information2.data.entity.aomori.AomoriData
import jp.covid19_kagawa.covid19information2.data.entity.aomori.AomoriInspectionDataItem

class AomoriRepository(
    private val aomoriAPi: AomoriAPi
) {
    fun fetchInspectData(): Single<AomoriData> = aomoriAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = aomoriAPi.downloadNewsData()
    fun fetchInspectionData(): Single<Array<AomoriInspectionDataItem>> =
        aomoriAPi.fetchInspectionData()

    fun fetchContactData(): Single<Array<AomoriContactDataItem>> = aomoriAPi.fetchConsultData()
    fun fetchCallData(): Single<Array<AomoriCallDataItem>> = aomoriAPi.fetchCallData()
}