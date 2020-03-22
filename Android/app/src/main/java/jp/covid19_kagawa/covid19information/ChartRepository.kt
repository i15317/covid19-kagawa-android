package jp.covid19_kagawa.covid19information

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.DownloaderApi
import jp.covid19_kagawa.covid19information.data.entity.InfectData


class ChartRepository(
    private val downloaderApi: DownloaderApi
) {
    fun getInspectData(): Single<InfectData> = downloaderApi.downloadFileWithFixedUrl()
}