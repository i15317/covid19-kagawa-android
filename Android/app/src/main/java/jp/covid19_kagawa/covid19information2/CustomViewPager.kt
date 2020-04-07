package jp.covid19_kagawa.covid19information2

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.PointerCoords
import android.view.MotionEvent.PointerProperties
import androidx.viewpager.widget.ViewPager


class CustomViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var isPagingEnabled = true
    private var onEnable = false

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (onEnable) { //有効にされた直後に一度だけ通る
            onEnable = false
            //以下の条件の時にsuper#onInterceptTouchEvent内で例外が発生するのを阻止
            if (ev.actionMasked == MotionEvent.ACTION_MOVE) {
                val properties = PointerProperties()
                properties.id = ev.getPointerId(0)
                val coords = PointerCoords()
                ev.getPointerCoords(0, coords)
                //ViewPagerにダミーTouchEventを流し込んで、ViewPager内で保持するPointerIDを更新する
                super.onInterceptTouchEvent(
                    MotionEvent.obtain(
                        ev.downTime,
                        ev.eventTime,
                        MotionEvent.ACTION_DOWN,
                        ev.pointerCount,
                        arrayOf(properties),
                        arrayOf(coords),
                        ev.metaState,
                        ev.buttonState,
                        ev.xPrecision,
                        ev.yPrecision,
                        ev.deviceId,
                        ev.edgeFlags,
                        ev.source,
                        ev.flags
                    )
                )
            }
        }
        return isPagingEnabled && super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isPagingEnabled && super.onTouchEvent(ev)
    }

    fun setPagingEnabled(isPagingEnabled: Boolean) {
        if (this.isPagingEnabled != isPagingEnabled) {
            onEnable = isPagingEnabled //有効化された、直後のonInterceptTouchで処理
        }
        this.isPagingEnabled = isPagingEnabled
    }

    fun getPagingEnabled(): Boolean {
        return isPagingEnabled
    }
}