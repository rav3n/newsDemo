package com.seldon.news.common.rubrics.data;

public class RubricEntity {

    private long id;
    private String name;

    public RubricEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
