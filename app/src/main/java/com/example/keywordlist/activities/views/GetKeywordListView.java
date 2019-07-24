package com.example.keywordlist.activities.views;

import java.util.List;

public interface GetKeywordListView extends ViewBase {

    void onSuccess(List<String> items);

    void onFailed(Throwable throwable);

}
