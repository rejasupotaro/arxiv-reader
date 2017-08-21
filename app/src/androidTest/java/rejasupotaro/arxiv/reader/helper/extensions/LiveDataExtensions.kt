package rejasupotaro.arxiv.reader.helper.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.waitAndGetValue(func: () -> Unit): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            data = t
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    func.invoke()
    latch.await(2, TimeUnit.SECONDS)
    return data
}
