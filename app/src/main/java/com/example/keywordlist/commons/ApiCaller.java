package com.example.keywordlist.commons;

import com.example.keywordlist.activities.views.GetKeywordListView;

import java.net.SocketTimeoutException;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiCaller {

    public static void getKeyWordList(GetKeywordListView view) {

        ServerTasks.getInstance().getApi()
                .getKeywordList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (view != null) view.onSuccess(response);
                }, error -> {
                    error.printStackTrace();

                    if (view == null) return;

                    if (error instanceof SocketTimeoutException) {
                        view.onConnectionError();
                    } else {
                        view.onFailed(error);
                    }
                });
    }

}
