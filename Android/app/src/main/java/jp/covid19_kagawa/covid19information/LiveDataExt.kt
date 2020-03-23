package jp.covid19_kagawa.covid19information

import android.os.Handler
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer<T> {
        it?.let { observer(it) }
    })
}

// https://kuluna.github.io/blog/post/20180705/

private const val CLICKABLE_DELAY_TIME = 100L

/**
 * Extension function for [View.setOnClickListener].
 * It prevents fast clicking by user.
 */
@Suppress("UNCHECKED_CAST")
fun <T: View> T.setSafeClickListener(listener: (it: T) -> Unit) {
    setOnClickListener { view ->
        if (view == null) return@setOnClickListener
        view.isEnabled = false

        Handler().postDelayed(
            { view.isEnabled = true },
            CLICKABLE_DELAY_TIME
        )

        listener.invoke(view as T)
    }
}