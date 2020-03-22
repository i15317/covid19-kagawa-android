package jp.covid19_kagawa


import jp.covid19_kagawa.entity.InspectionData
import jp.covid19_kagawa.flux.Dispatcher
import jp.covid19_kagawa.flux.Store
import jp.covid19_kagawa.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class ChartStore(dispatcher: Dispatcher) : Store(dispatcher) {

    private val inspectionData = mutableListOf<InspectionData>()

    val loadingState = StoreLiveData<Boolean>()
    val loadedRepositoryListState = StoreLiveData<List<InspectionData>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: ChartAction) {
        when (action) {

            is ChartAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is ChartAction.FetchInfectData -> {
                loadedRepositoryListState.postValue(inspectionData)
            }
        }
    }

}