package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.ContactAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.ContactRepository

class ContactActionCreator(
    private val contactRepository: ContactRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<ContactAction>(dispatcher) {

    fun fetchContactData() =
        contactRepository.getContactData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(ContactAction.ShowLoading(true)) }
            .doFinally { dispatch(ContactAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(ContactAction.FetchContactData(it))
                },
                onError = {
                    //  Timber.e(it)
                }
            )
}