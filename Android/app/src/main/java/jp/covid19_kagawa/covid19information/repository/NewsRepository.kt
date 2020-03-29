package jp.covid19_kagawa.covid19information.repository

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.data.mapper.KagawaMapper
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.data.repository.*
import jp.covid19_kagawa.covid19information.entity.NewsEntity

class NewsRepository(
    private val tokyoRepository: TokyoRepository,
    private val kagawaRepository: KagawaRepository,
    private val aomoriRepository: AomoriRepository,
    private val iwateRepository: IwateRepository,
    private val miyagiRepository: MiyagiRepository,
    private val ibarakiRepository: IbarakiRepository,
    private val gummaRepository: GummaRepository,
    private val chibaRepository: ChibaRepository,
    private val niigataRepository: NiigataRepository
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
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.KAGAWA -> {
                    kagawaRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.AOMORI -> {
                    aomoriRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.IWATE -> {
                    iwateRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.MIYAGI -> {
                    miyagiRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.IBARAKI -> {
                    ibarakiRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.GUMMA -> {
                    gummaRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.CHIBA -> {
                    chibaRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
                            }
                        )
                }
                Prefecture.NIIGATA -> {
                    niigataRepository.fetchNewsData().subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(KagawaMapper.getNewsData(it.newsItems))
                            },
                            onError = {
                                //Timber.e(it)
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