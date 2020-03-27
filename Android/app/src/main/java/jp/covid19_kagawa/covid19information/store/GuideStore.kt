package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.GuideAction
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class GuideStore(dispatcher: Dispatcher) : Store(dispatcher) {

    //    private val inspectionData = mutableListOf<InspectionData>()
    val loadingState = StoreLiveData<Boolean>()
    val titleMessage = StoreLiveData<String>()
    val telephoneLink = StoreLiveData<String>()
    val webSiteLink = StoreLiveData<String>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: GuideAction) {
        when (action) {
            is GuideAction.ShowLoading -> {
                loadingState.postValue(action.isLoading)
            }
            is GuideAction.ChangeGuideScene -> {
                titleMessage.postValue(action.title)
            }

            is GuideAction.GetWebLinks -> {
                webSiteLink.postValue(action.webEntity.centerLink)
            }
        }
    }

}