package com.example.keywordlist.activities.presenters;

import com.example.keywordlist.commons.ApiCaller;
import com.example.keywordlist.activities.views.GetKeywordListView;

public class MainActivityPresenter {

    private GetKeywordListView getKeywordListView;

    public MainActivityPresenter(GetKeywordListView getKeywordListView) {
        this.getKeywordListView = getKeywordListView;
    }

    public void getKeywordList() {
        ApiCaller.getKeyWordList(getKeywordListView);
    }

}
