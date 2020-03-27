package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.data.entity.tokyo.InfectData
import jp.covid19_kagawa.covid19information.data.entity.NewsData

class TokyoRepository(
    private val tokyoAPi: TokyoAPi
) {
    fun fetchInspectData(): Single<InfectData> = tokyoAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = tokyoAPi.downloadNewsData()
}