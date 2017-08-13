package rejasupotaro.arxiv.reader.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun <X, Y> LiveData<X>.create(func: (X) -> LiveData<Y>): LiveData<Y> {
    val result = MutableLiveData<Y>()
    return result
}

fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> {
    return Transformations.map(this) {
        func.invoke(it)
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
