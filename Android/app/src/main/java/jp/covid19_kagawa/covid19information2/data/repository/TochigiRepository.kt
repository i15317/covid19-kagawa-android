package jp.covid19_kagawa.covid19information2.data.repository

import io.reactivex.Single
import jp.covid19_kagawa.covid19information2.data.api.tochigi.TochigiAPi
import jp.covid19_kagawa.covid19information2.data.entity.NewsData
import jp.covid19_kagawa.covid19information2.data.entity.tochigi.TochigiData


class TochigiRepository(
    private val tochigiAPi: TochigiAPi
) {
    fun fetchInspectData(): Single<TochigiData> = tochigiAPi.downloadFileWithFixedUrl()
    fun fetchNewsData(): Single<NewsData> = tochigiAPi.downloadNewsData()
}