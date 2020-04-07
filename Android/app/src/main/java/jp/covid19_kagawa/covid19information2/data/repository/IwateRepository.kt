package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.iwate.IwateAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.iwate.IwateData

class IwateRepository(
    private val iwateAPi: IwateAPi
) {
    fun fetchInspectData(): Single<IwateData> = iwateAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = iwateAPi.downloadNewsData()
}