package jp.covid19_kagawa.covid19information2.repository

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.data.mapper.*
import jp.covid19_kagawa.covid19information2.data.repository.*
import jp.covid19_kagawa.covid19information2.entity.InspectionSummary


class InspectionRepository(
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
    fun fetchInspectionData(prefecture: Prefecture): Single<InspectionSummary> {
        return Single.create<InspectionSummary> { emitter ->
            when (prefecture) {
                Prefecture.TOKYO -> {
                    tokyoRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    TokyoMapper.getInspectionSummaryData(
                                        it
                                    )
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
                                    KagawaMapper.getInspectionSummaryData(
                                        it
                                    )
                                )
                            },
                            onError = { emitter.onError(it) }
                        )
                }
                Prefecture.AOMORI -> {
                    aomoriRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    AomoriMapper.getInspectionSummaryData(
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
                                    IwateMapper.getInspectionSummaryData(
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
                                    MiyagiMapper.getInspectionSummaryData(
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
                                    IbarakiMapper.getInspectionSummaryData(
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
                                    GummaMapper.getInspectionSummaryData(
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
                                    ChibaMapper.getInspectionSummaryData(
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
                                    NiigataMapper.getInspectionSummaryData(
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