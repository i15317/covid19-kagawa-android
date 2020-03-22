package jp.covid19_kagawa.covid19information.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.data.entity.InfectData


class ChartRepository(
    private val tokyoAPi: TokyoAPi
) {
    fun getInspectData(): Single<InfectData> = tokyoAPi.downloadFileWithFixedUrl()

}