package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.entity.ContactData


sealed class ContactAction {
    class FetchContactData(val data: ContactData) : ContactAction()
    class ShowLoading(val isLoading: Boolean) : ContactAction()

}