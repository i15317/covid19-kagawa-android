package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.InfectionAction
import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.SummaryEntity
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
        private const val SUB_APPEND = "人"
    }

    var canFetchMore = false
        private set

    var pageNum = INITIAL_PAGE
        private set

    private val loadingInfectionList = mutableListOf<SummaryEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val loadedInfectionData = StoreLiveData<List<SummaryEntity>>()


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

        }
    }

    private fun makeInfectionList(infectionSummary: InfectionSummary): List<SummaryEntity> {

//        val inspectedEntity = InfectionEntity(
//            MAIN_TITLE,
//            SUB_TITLE,
//            infectionSummary.inspection_num.toString(),
//            ""
//        )

        //陽性患者数
        val infectedEntity = SummaryEntity(
            INFECTED_TITLE,
            SUB_TITLE,
            infectionSummary.infect_num.toString() + SUB_APPEND,
            ""
        )

        //入院中
        val hospitalEntity = SummaryEntity(
            HOSPITAL_TITLE,
            SUB_TITLE,
            infectionSummary.infect_hospital.toString() + SUB_APPEND,
            ""
        )

        //軽症
        val lightEntity = SummaryEntity(
            LIGHT_TITLE,
            SUB_TITLE,
            infectionSummary.infect_light.toString() + SUB_APPEND,
            ""
        )

        //重症
        val heavyEntity = SummaryEntity(
            HEAVY_TITLE,
            SUB_TITLE,
            infectionSummary.infect_heavy.toString() + SUB_APPEND,
            ""
        )

        //死亡
        val diedEntity = SummaryEntity(
            DIED_TITLE,
            SUB_TITLE,
            infectionSummary.infect_died.toString() + SUB_APPEND,
            ""
        )

        //退院
        val recoverEntity = SummaryEntity(
            RECOVERED_TITLE,
            SUB_TITLE,
            infectionSummary.inafect_recover.toString() + SUB_APPEND,
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