package com.softwareuk.common.mvp

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BasePresenter<V : MvpView> : ViewModel(),
    MvpPresenter<V> {

    protected var view: V? = null
        private set

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detach() {
        view = null
    }

}
