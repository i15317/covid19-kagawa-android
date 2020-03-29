package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.chiba.ChibaAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.chiba.ChibaData

class ChibaRepository(
    private val chibaAPi: ChibaAPi
) {
    fun fetchInspectData(): Single<ChibaData> = chibaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = chibaAPi.downloadNewsData()
}