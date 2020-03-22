package jp.covid19_kagawa.flux

abstract class ActionCreator<Action : Any>(
    private val dispatcher: Dispatcher
) {
    fun dispatch(action: Action) = dispatcher.dispatch(action)
}
