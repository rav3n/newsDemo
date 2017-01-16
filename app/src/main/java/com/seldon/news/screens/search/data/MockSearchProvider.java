package com.seldon.news.screens.search.data;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


public class MockSearchProvider implements SearchProvider {
    @Override public Observable<List<SearchStringEntity>> search(String target) {
        List<SearchStringEntity> list = new ArrayList();
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        list.add(new SearchStringEntity(getRandomString(target)));
        return Observable.just(list);
    }

    private static String getRandomString(String source) {
        return source + " " +System.currentTimeMillis();
    }
}
