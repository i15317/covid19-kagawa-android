package jp.covid19_kagawa.covid19information2.store


import jp.covid19_kagawa.covid19information2.action.EntranceAction
import jp.covid19_kagawa.covid19information2.entity.EntranceEntry
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.flux.Store
import jp.covid19_kagawa.covid19information2.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class EntranceStore(dispatcher: Dispatcher) : Store(dispatcher) {

    private val entranceData = mutableListOf<EntranceEntry>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedRepositoryListState = StoreLiveData<List<EntranceEntry>>()
    val entranceNum = StoreLiveData<Int>()
    val entranceDate = StoreLiveData<String>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: EntranceAction) {
        when (action) {
            is EntranceAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is EntranceAction.FetchEntranceData -> {
                entranceData.clear()
                entranceData.addAll(action.data.entries)
                loadedRepositoryListState.postValue(entranceData)
                entranceDate.postValue(action.data.date)
                entranceNum.postValue(entranceData.sumBy { it.value })
            }
        }
    }

}