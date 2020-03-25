package jp.covid19_kagawa.covid19information.store


import jp.covid19_kagawa.covid19information.action.AreaAction
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import jp.digital_future.cameraxsample.room.entity.AreaEntity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AreaStore(dispatcher: Dispatcher) : Store(dispatcher) {

    private val areaList = mutableListOf<AreaEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedAreaList = StoreLiveData<List<AreaEntity>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: AreaAction) {
        when (action) {

            is AreaAction.ShowLoading -> {
                //Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is AreaAction.SelectPrefAreaAction -> {
                areaList.clear()
                areaList.addAll(action.areaList)
                loadedAreaList.postValue(areaList)
            }
        }
    }
}