package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.niigata.NiigataAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.niigata.NiigataData

class NiigataRepository(
    private val niigataAPi: NiigataAPi
) {
    fun fetchInspectData(): Single<NiigataData> = niigataAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = niigataAPi.downloadNewsData()
}