package jp.covid19_kagawa.covid19information.repository

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.data.repository.TokyoRepository
import jp.covid19_kagawa.covid19information.entity.NewsEntity
import timber.log.Timber

class NewsRepository(
    private val tokyoRepository: TokyoRepository
) {
    fun fetchNewsData(prefecture: Prefecture): Single<List<NewsEntity>> {
        return Single.create<List<NewsEntity>> { emitter ->
            when (prefecture) {
                Prefecture.TOKYO -> {
                    tokyoRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(TokyoMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                Timber.e(it)
                            }
                        )
                }
                else -> {
                    emitter.onError(Throwable(message = "Type Error!"))
                }
            }
        }
    }
}