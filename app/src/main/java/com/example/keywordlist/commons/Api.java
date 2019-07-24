package com.example.keywordlist.commons;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface Api {

    String URL_GET_KEYWORD_LIST = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/keywords.json";

    @GET(URL_GET_KEYWORD_LIST)
    Observable<List<String>> getKeywordList();

}
