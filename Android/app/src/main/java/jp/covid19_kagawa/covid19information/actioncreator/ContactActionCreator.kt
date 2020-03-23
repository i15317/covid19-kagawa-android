package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.ContactAction
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.ChartRepository
import timber.log.Timber

class ContactActionCreator(
    private val repository: ChartRepository,
    dispatcher: Dispatcher
) : ActionCreator<ContactAction>(dispatcher) {
    fun fetchContactData() =
        repository.getInspectData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(ContactAction.ShowLoading(true)) }
            .doFinally { dispatch(ContactAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(ContactAction.FetchContactData(TokyoMapper.getContactData(it)))
                },
                onError = { Timber.e(it) }
            )
}