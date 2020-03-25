package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.PrefectureAction
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import jp.digital_future.cameraxsample.room.entity.PrefectureEntity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class PrefectureStore(dispatcher: Dispatcher) : Store(dispatcher) {

    private val areaList = mutableListOf<PrefectureEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedAreaList = StoreLiveData<List<PrefectureEntity>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: PrefectureAction) {
        when (action) {
            is PrefectureAction.ShowLoading -> {
                //Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is PrefectureAction.SelectPrefAction -> {
                areaList.clear()
                areaList.addAll(action.prefList)
                loadedAreaList.postValue(areaList)
            }
        }
    }
}