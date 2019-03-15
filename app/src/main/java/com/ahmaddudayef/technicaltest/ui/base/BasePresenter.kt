package com.ahmaddudayef.technicaltest.ui.base

import com.ahmaddudayef.technicaltest.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject


/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
open class BasePresenter<V : MvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
): MvpPresenter<V> {

    var mvpView: V? = null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        this.mvpView = null
    }

    fun isViewAttached(): Boolean {
        return mvpView != null
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

    fun handleApiError(throwable: Throwable) {
        if (throwable is HttpException) {
            when (throwable.code()) {
                401 -> {
                    mvpView?.showError("sessionId expired or invalid.")
                }
            }
        } else {
            mvpView?.showError(throwable.message ?: "")
        }
    }

}