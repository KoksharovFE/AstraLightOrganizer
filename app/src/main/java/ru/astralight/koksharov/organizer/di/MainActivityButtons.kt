package ru.astralight.koksharov.organizer.di

import android.support.constraint.Constraints.TAG
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable



public fun startRStream() {
    val TAG = "ASTRA MAIN ACTIVITY BUTTONS"
//Create an Observable//
    val myObservable = getObservable()
//Create an Observer//
    val myObserver : Observer<String> = getObserver()
//Subscribe myObserver to myObservable//
    myObservable
        .subscribe(myObserver)
}
private fun getObserver(): Observer <String>{
    return object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
        }

        //Every time onNext is called, print the value to Android Studio’s Logcat//
        override fun onNext(s: String) {
            Log.d(TAG, "onNext: $s")
        }

        //Called if an exception is thrown//
        override fun onError(e: Throwable) {
            Log.e(TAG, "onError: " + e.message)
        }

        //When onComplete is called, print the following to Logcat//
        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }
    }
}

//Give myObservable some data to emit//

private fun getObservable(): Observable<String> {
    return Observable.just("1", "2", "3", "4", "5")
}
