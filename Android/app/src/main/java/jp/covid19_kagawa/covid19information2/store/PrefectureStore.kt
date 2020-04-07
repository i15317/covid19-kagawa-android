package jp.covid19_kagawa.covid19information2.store

import jp.covid19_kagawa.covid19information2.action.PrefectureAction
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.flux.Store
import jp.covid19_kagawa.covid19information2.flux.StoreLiveData
import jp.covid19_kagawa.covid19information2.room.entity.PrefectureEntity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class PrefectureStore(dispatcher: Dispatcher) : Store(dispatcher) {
    val emptyEntity = PrefectureEntity(0, "", "表示可能な県がありません", "-1")
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
                if (action.prefList.isEmpty()) {
                    areaList.clear()
                    areaList.add(emptyEntity)
                    loadedAreaList.postValue(areaList)
                } else {
                    areaList.clear()
                    areaList.addAll(action.prefList)
                    loadedAreaList.postValue(areaList)
                }
            }
        }
    }
}