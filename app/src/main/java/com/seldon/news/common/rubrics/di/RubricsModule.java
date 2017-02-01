package com.seldon.news.common.rubrics.di;

import com.seldon.news.common.base.data.NewsResponse;
import com.seldon.news.common.rubrics.data.ApiRubricsProvider;
import com.seldon.news.common.rubrics.data.RubricEntity;
import com.seldon.news.common.rubrics.domain.RubricsInteractor;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class RubricsModule {

    @Provides public ApiRubricsProvider providerApi() {
        return new ApiRubricsProvider() {
            @Override public Observable<NewsResponse<RubricEntity[]>> getRubrics() {
                RubricEntity[] rubricEntities = new RubricEntity[] {
                        new RubricEntity(0, "хей хоп ла ла лей"),
                        new RubricEntity(1, "ч0 каво"),
                };
                return Observable.just(new NewsResponse<>(rubricEntities));
            }

            @Override public Observable<NewsResponse<RubricEntity[]>> getCustomUserRubric() {
                RubricEntity[] rubricEntities = new RubricEntity[] {
                        new RubricEntity(2, "вжух и новая рубрика"),
                        new RubricEntity(3, "это разборка питерская"),
                };
                return Observable.just(new NewsResponse<>(rubricEntities));
            }
        };
    }

    @Provides public RubricsInteractor provideRubricsInteractor(ApiRubricsProvider provider) {
        return new RubricsInteractor(provider);
    }
}
