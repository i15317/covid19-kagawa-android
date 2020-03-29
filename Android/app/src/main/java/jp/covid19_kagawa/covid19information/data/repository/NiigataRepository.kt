package jp.covid19_kagawa.covid19information.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.niigata.NiigataAPi
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.niigata.NiigataData

class NiigataRepository(
    private val niigataAPi: NiigataAPi
) {
    fun fetchInspectData(): Single<NiigataData> = niigataAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = niigataAPi.downloadNewsData()
}