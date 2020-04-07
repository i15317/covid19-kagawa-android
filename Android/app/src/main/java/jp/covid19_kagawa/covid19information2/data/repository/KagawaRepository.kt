package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.kagawa.KagawaAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.kagawa.KagawaData

class KagawaRepository(
    private val kagawaAPi: KagawaAPi
) {
    fun fetchInspectData(): Single<KagawaData> = kagawaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = kagawaAPi.downloadNewsData()
}