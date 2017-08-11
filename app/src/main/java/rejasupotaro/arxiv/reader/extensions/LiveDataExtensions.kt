package rejasupotaro.arxiv.reader.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations

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
