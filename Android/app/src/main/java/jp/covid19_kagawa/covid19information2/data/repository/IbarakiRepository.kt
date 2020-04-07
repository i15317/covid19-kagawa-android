package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.ibaraki.IbarakiAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.ibaraki.IbarakiData

class IbarakiRepository(
    private val ibarakiAPi: IbarakiAPi
) {
    fun fetchInspectData(): Single<IbarakiData> = ibarakiAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = ibarakiAPi.downloadNewsData()
}