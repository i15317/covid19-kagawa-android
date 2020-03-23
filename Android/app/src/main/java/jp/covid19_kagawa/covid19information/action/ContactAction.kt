package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.ContactData


sealed class ContactAction {
    class FetchContactData(val data: ContactData) : ContactAction()
    class ShowLoading(val isLoading: Boolean) : ContactAction()

}