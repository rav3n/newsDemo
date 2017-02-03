package com.seldon.news.common.rubrics.data;

import com.google.gson.annotations.SerializedName;

public class RubricsResponse {

    @SerializedName("Success")
    private boolean success;

    RubricEntity[] rubrics;

    public RubricEntity[] getRubrics() {
        return rubrics;
    }

    public boolean isSuccess() {
        return success;
    }

}
