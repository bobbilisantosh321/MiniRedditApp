package com.santosh.miniredditapp.viewmodel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * All view models must extend this base class
 * @param <T>
 */

public class BaseViewModel<T extends IView> {

    protected CompositeDisposable compositeDisposable;
    T view;

    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void attach(T view) {
        this.view = view;
    }

    public void detach() {
        view = null;
    }

    /**
     *  Using clear will clear all disposables
     */
    public void clearSubscriptions() {
        compositeDisposable.clear();
    }

}
