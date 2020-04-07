package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.gumma.GummaAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.gumma.GummaData

class GummaRepository(
    private val gummmaAPi: GummaAPi
) {
    fun fetchInspectData(): Single<GummaData> = gummmaAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = gummmaAPi.downloadNewsData()
}