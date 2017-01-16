package com.seldon.news.screens.search.data;


import java.util.List;

import rx.Observable;

public interface SearchProvider {
    Observable<List<SearchStringEntity>> search(String target);
}
