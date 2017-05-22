package br.com.doublef.pipedriveclient.remote;


import rx.Subscription;

public class RxUnsubscriber {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
