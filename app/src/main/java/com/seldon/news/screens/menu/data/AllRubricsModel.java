package com.seldon.news.screens.menu.data;

import com.seldon.news.common.rubrics.data.RubricEntity;

public class AllRubricsModel {

    public RubricEntity[] generalRubrics;
    public RubricEntity[] userRubrics;

    public AllRubricsModel(RubricEntity[] generalRubrics, RubricEntity[] userRubrics) {
        this.generalRubrics = generalRubrics;
        this.userRubrics = userRubrics;
    }
}
