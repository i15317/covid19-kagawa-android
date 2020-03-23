package jp.covid19_kagawa.covid19information.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.data.entity.InfectData
import jp.covid19_kagawa.covid19information.data.entity.NewsData


class InspectionRepository(
    private val tokyoAPi: TokyoAPi
) {
    fun getInspectionData(): Single<InfectData> = tokyoAPi.downloadFileWithFixedUrl()
}