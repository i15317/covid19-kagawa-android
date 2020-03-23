package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.InfectionAction
import jp.covid19_kagawa.covid19information.entity.InfectionEntity
import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.NewsEntity
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber


class InfectionStore(dispatcher: Dispatcher) : Store(dispatcher) {
    companion object {
        private const val INITIAL_PAGE = 1
        private const val MAIN_TITLE = "累計患者数"
        private const val INFECTED_TITLE = "陽性患者数"
        private const val HOSPITAL_TITLE = "入院患者数"
        private const val LIGHT_TITLE = "軽症・中等症"
        private const val HEAVY_TITLE = "重症"
        private const val RECOVERED_TITLE = "退院"
        private const val DIED_TITLE = "死亡"
        private const val SUB_TITLE = "（累計）"
    }

    var canFetchMore = false
        private set

    var pageNum = INITIAL_PAGE
        private set

    private val loadingNewsList = mutableListOf<NewsEntity>()
    private val loadingInfectionList = mutableListOf<InfectionEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedInfectionData = StoreLiveData<List<InfectionEntity>>()
    val loadedNewsList = StoreLiveData<List<NewsEntity>>()
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: InfectionAction) {
        when (action) {

            is InfectionAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is InfectionAction.FetchInfectionData -> {
                loadingInfectionList.clear()
                loadingInfectionList.addAll(makeInfectionList(action.data))
                loadedInfectionData.postValue(loadingInfectionList)
            }

            is InfectionAction.FetchNewsData -> {
                canFetchMore = action.data.isNotEmpty()
                pageNum++
                loadingNewsList.clear()
                loadingNewsList.addAll(action.data)
                loadedNewsList.postValue(loadingNewsList)
            }
        }
    }

    private fun makeInfectionList(infectionSummary: InfectionSummary): List<InfectionEntity> {

//        val inspectedEntity = InfectionEntity(
//            MAIN_TITLE,
//            SUB_TITLE,
//            infectionSummary.inspection_num.toString(),
//            ""
//        )

        //陽性患者数
        val infectedEntity = InfectionEntity(
            INFECTED_TITLE,
            SUB_TITLE,
            infectionSummary.infect_num.toString(),
            ""
        )

        //入院中
        val hospitalEntity = InfectionEntity(
            HOSPITAL_TITLE,
            SUB_TITLE,
            infectionSummary.infect_hospital.toString(),
            ""
        )

        //軽症
        val lightEntity = InfectionEntity(
            LIGHT_TITLE,
            SUB_TITLE,
            infectionSummary.infect_light.toString(),
            ""
        )

        //重症
        val heavyEntity = InfectionEntity(
            HEAVY_TITLE,
            SUB_TITLE,
            infectionSummary.infect_heavy.toString(),
            ""
        )

        //死亡
        val diedEntity = InfectionEntity(
            DIED_TITLE,
            SUB_TITLE,
            infectionSummary.infect_died.toString(),
            ""
        )

        //退院
        val recoverEntity = InfectionEntity(
            RECOVERED_TITLE,
            SUB_TITLE,
            infectionSummary.inafect_recover.toString(),
            ""
        )
        return mutableListOf(
      //      inspectedEntity,
            infectedEntity,
            hospitalEntity,
            lightEntity,
            heavyEntity,
            diedEntity,
            recoverEntity
        )
    }
}