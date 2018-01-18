package com.dongumen.nickolas.ownweather2.presenters;

import com.dongumen.nickolas.ownweather2.views.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nickolas on 13.01.2018.
 */

public class BasePresenter<T extends BaseView> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void destroy() {
        compositeSubscription.clear();
    }

    protected Subscription subscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
        return subscription;
    }
}

