package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.aomori.AomoriAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.aomori.AomoriData

class AomoriRepository(
    private val aomoriAPi: AomoriAPi
) {
    fun fetchInspectData(): Single<AomoriData> = aomoriAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = aomoriAPi.downloadNewsData()
}