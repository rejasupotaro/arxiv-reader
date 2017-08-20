package rejasupotaro.arxiv.reader.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun <T> observable(func: () -> T): LiveData<T> {
    val liveData = MutableLiveData<T>()
    async(CommonPool) {
        liveData.postValue(func.invoke())
    }
    return liveData
}

fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> {
    return Transformations.map(this) {
        func.invoke(it)
    }
}

fun <X, Y> LiveData<out Iterable<X>>.flatMap(func: (X) -> Y): LiveData<List<Y>> {
    return Transformations.map(this) {
        it.map { func.invoke(it) }
    }
}

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this) {
        func.invoke(it)
    }
}

fun <X, Y> LiveData<X>.deferredMap(func: (X) -> Y): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    result.addSource(this) { x ->
        async(CommonPool) {
            x?.let {
                result.postValue(func.invoke(x))
            } ?: run {
                result.postValue(null)
            }
        }
    }
    return result
}
