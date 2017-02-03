package com.seldon.news.screens.menu.data;

import com.seldon.news.common.rubrics.data.RubricEntity;

public class AllRubricsModel {

    private RubricEntity[] generalRubrics;
    private RubricEntity[] userRubrics;
    private RubricEntity favoritesRubric;
    private RubricEntity readInFutureRubric;

    public AllRubricsModel(RubricEntity[] generalRubrics, RubricEntity[] userRubrics) {
        this.generalRubrics = generalRubrics;
        this.userRubrics = userRubrics;
    }

    public RubricEntity[] getGeneralRubrics() {
        return generalRubrics;
    }

    public RubricEntity[] getUserRubrics() {
        return userRubrics;
    }

    public RubricEntity getFavoritesRubric() {
        return favoritesRubric;
    }

    public RubricEntity getReadInFutureRubric() {
        return readInFutureRubric;
    }

    public void setFavoritesRubric(RubricEntity favoritesRubric) {
        this.favoritesRubric = favoritesRubric;
    }

    public void setReadInFutureRubric(RubricEntity readInFutureRubric) {
        this.readInFutureRubric = readInFutureRubric;
    }
}
