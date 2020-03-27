package jp.covid19_kagawa.covid19information.repository

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.data.mapper.KagawaMapper
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.data.repository.KagawaRepository
import jp.covid19_kagawa.covid19information.data.repository.TokyoRepository
import jp.covid19_kagawa.covid19information.entity.InspectionDetailSummary

class InspectionDetailRepository(
    private val tokyoRepository: TokyoRepository,
    private val kagawaRepository: KagawaRepository
) {
    fun fetchInspectionDetailData(prefecture: Prefecture): Single<List<InspectionDetailSummary>> {
        return Single.create<List<InspectionDetailSummary>> { emitter ->
            when (prefecture) {
                Prefecture.TOKYO -> {
                    tokyoRepository.fetchInspectData()
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                            onSuccess = {
                                emitter.onSuccess(
                                    TokyoMapper.getInspectionDetailData(
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
                                    KagawaMapper.getInspectionDetailData(
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