package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.InspectionDetailAction
import jp.covid19_kagawa.covid19information.entity.InspectionDetailSummary
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class InspectionDetailStore(dispatcher: Dispatcher) : Store(dispatcher) {

    private val inspectionData = mutableListOf<InspectionDetailSummary>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedRepositoryListState = StoreLiveData<List<InspectionDetailSummary>>()
    val inspectionNum = StoreLiveData<Int>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: InspectionDetailAction) {
        when (action) {

            is InspectionDetailAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is InspectionDetailAction.FetchInspectionDetailData -> {
                inspectionData.clear()
                inspectionData.addAll(action.list)
                loadedRepositoryListState.postValue(inspectionData)
                inspectionNum.postValue(inspectionData.sumBy { it.inside + it.outside })
            }
        }
    }

}