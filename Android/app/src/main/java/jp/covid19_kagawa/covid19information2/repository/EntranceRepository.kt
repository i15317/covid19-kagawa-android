package jp.covid19_kagawa.covid19information2.repository

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.data.mapper.*
import jp.covid19_kagawa.covid19information2.data.repository.*
import jp.covid19_kagawa.covid19information2.entity.EntranceData

class EntranceRepository(
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
    fun fetchEntranceData(prefecture: Prefecture): Single<EntranceData> {
        return Single.create<EntranceData> { emitter ->
            when (prefecture) {
                Prefecture.TOKYO -> {
                    tokyoRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    TokyoMapper.getEntranceData(it)
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.KAGAWA -> {
                    kagawaRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    KagawaMapper.getEntranceData(it)
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.AOMORI -> {
                    aomoriRepository.fetchContactData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    AomoriMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.IWATE -> {
                    iwateRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    IwateMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.MIYAGI -> {
                    miyagiRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    MiyagiMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.IBARAKI -> {
                    ibarakiRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    IbarakiMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.GUMMA -> {
                    gummaRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    GummaMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.CHIBA -> {
                    chibaRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    ChibaMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }

                Prefecture.NIIGATA -> {
                    niigataRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    NiigataMapper.getEntranceData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                else -> {
                    emitter.onError(Throwable(message = "Type Error!"))
                }
            }
        }
    }
}