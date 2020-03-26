package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.InspectionAction
import jp.covid19_kagawa.covid19information.entity.InspectionSummary
import jp.covid19_kagawa.covid19information.entity.SummaryEntity
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber


class InspectionStore(dispatcher: Dispatcher) : Store(dispatcher) {
    companion object {
        private const val INITIAL_PAGE = 1
        private const val MAIN_TITLE = "総検査患者人数"
        private const val COUNT_TITLE = "総検査件数"
        private const val INSIDE_TITLE = "地域内"
        private const val OUTSIDE_TITLE = "地域外"
        private const val SUB_TITLE = "（累計）"
    }

    var canFetchMore = false
        private set

    var pageNum = INITIAL_PAGE
        private set

    private val loadingInspectionList = mutableListOf<SummaryEntity>()
    val loadingState = StoreLiveData<Boolean>()
    val updatedDate = StoreLiveData<String>()
    val loadedInspectionData = StoreLiveData<List<SummaryEntity>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: InspectionAction) {
        when (action) {

            is InspectionAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }
            is InspectionAction.FetchInspectionData -> {
                loadingInspectionList.clear()
                loadingInspectionList.addAll(makeInspectionList(action.data))
                loadedInspectionData.postValue(loadingInspectionList)
                updatedDate.postValue(action.data.date + "　時点")

            }

        }
    }

    private fun makeInspectionList(InspectionSummary: InspectionSummary): List<SummaryEntity> {
        //検査実施人数
        val totalEntity = SummaryEntity(
            MAIN_TITLE,
            SUB_TITLE,
            InspectionSummary.value,
            ""
        )

        //検査実施件数
        val countEntity = SummaryEntity(
            COUNT_TITLE,
            SUB_TITLE,
            InspectionSummary.count_inspection,
            ""
        )

        //地域内住民
        val insideEntity = SummaryEntity(
            INSIDE_TITLE,
            SUB_TITLE,
            InspectionSummary.value_inside,
            ""
        )

        //地域外住民
        val outsideEntity = SummaryEntity(
            OUTSIDE_TITLE,
            SUB_TITLE,
            InspectionSummary.value_outside,
            ""
        )

        return mutableListOf(
            totalEntity,
            countEntity,
            insideEntity,
            outsideEntity
        )
    }
}