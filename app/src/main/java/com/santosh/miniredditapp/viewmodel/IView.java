package com.santosh.miniredditapp.viewmodel;

/**
 * View part of MVVM, the RedditMainAcitivity implements this
 * and is is used for interaction between ViewModel and Activity
 */
public interface IView {

    void error(Throwable e);

    void error();
}
