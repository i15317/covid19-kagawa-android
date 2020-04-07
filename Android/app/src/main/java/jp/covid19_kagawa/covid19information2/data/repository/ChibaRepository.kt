package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.chiba.ChibaAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.chiba.ChibaData

class ChibaRepository(
    private val chibaAPi: ChibaAPi
) {
    fun fetchInspectData(): Single<ChibaData> = chibaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = chibaAPi.downloadNewsData()
}