package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.gumma.GummaAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.gumma.GummaData

class GummaRepository(
    private val gummmaAPi: GummaAPi
) {
    fun fetchInspectData(): Single<GummaData> = gummmaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = gummmaAPi.downloadNewsData()
}