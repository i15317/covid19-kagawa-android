package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.kagawa.KagawaAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.kagawa.KagawaData

class KagawaRepository(
    private val kagawaAPi: KagawaAPi
) {
    fun fetchInspectData(): Single<KagawaData> = kagawaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = kagawaAPi.downloadNewsData()
}