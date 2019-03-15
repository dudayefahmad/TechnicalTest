package com.ahmaddudayef.technicaltest.ui.main

import com.ahmaddudayef.technicaltest.data.DataManager
import com.ahmaddudayef.technicaltest.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
class MainPresenter<V: MainMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
    ): BasePresenter<V>(dataManager, compositeDisposable), MainMvpPresenter<V> {

    override fun getJokesRandom() {
        mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.getJokesRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    mvpView?.updateJokesRandom(result.value)
                }, { throwable ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    mvpView?.showError(throwable.message!!)
                    Timber.e(throwable.message)
                })
        )
    }

}