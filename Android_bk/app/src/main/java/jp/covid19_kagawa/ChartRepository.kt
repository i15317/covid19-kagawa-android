package jp.covid19_kagawa

import io.reactivex.Single
import jp.covid19_kagawa.data.api.DownloaderApi
import jp.covid19_kagawa.data.entity.InfectData


class ChartRepository(
    private val downloaderApi: DownloaderApi
) {
    fun getInspectData(): Single<InfectData> = downloaderApi.downloadFileWithFixedUrl()
}