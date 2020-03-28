package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.miyagi.MiyagiAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.miyagi.MiyagiData

class MiyagiRepository(
    private val miyagiAPi: MiyagiAPi
) {
    fun fetchInspectData(): Single<MiyagiData> = miyagiAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = miyagiAPi.downloadNewsData()
}