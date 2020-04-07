package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.miyagi.MiyagiAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.miyagi.MiyagiData

class MiyagiRepository(
    private val miyagiAPi: MiyagiAPi
) {
    fun fetchInspectData(): Single<MiyagiData> = miyagiAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = miyagiAPi.downloadNewsData()
}