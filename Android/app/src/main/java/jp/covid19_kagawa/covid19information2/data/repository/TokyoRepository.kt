package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.tokyo.InfectData

class TokyoRepository(
    private val tokyoAPi: TokyoAPi
) {
    fun fetchInspectData(): Single<InfectData> = tokyoAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = tokyoAPi.downloadNewsData()
}