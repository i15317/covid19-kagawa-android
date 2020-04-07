package jp.covid19_kagawa.covid19information2.store


import jp.covid19_kagawa.covid19information2.action.AreaAction
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.flux.Store
import jp.covid19_kagawa.covid19information2.flux.StoreLiveData
import jp.covid19_kagawa.covid19information2.room.entity.AreaEntity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AreaStore(dispatcher: Dispatcher) : Store(dispatcher) {
    companion object {
        private const val DEFAULT_MESSAGE = "現在の設定：未設定"
        private const val TEMPLATE_MESSAGE = "現在の設定："
    }

    private val areaList = mutableListOf<AreaEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedAreaList = StoreLiveData<List<AreaEntity>>()
    val loadingCurrentPrefectureName = StoreLiveData<String>().apply {
        this.postValue(DEFAULT_MESSAGE)
    }

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

            is AreaAction.GetCurrentPrefectureNameAction -> {
                loadingCurrentPrefectureName.postValue(TEMPLATE_MESSAGE + action.prefName)
            }
        }
    }
}