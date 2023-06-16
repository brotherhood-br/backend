package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.BudgetsGoalEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.model.CreateGoal;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateBudgetService {

    @Inject
    private GetUserDataProvider getUserDataProvider;

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveBudgetDataProvider saveBudgetDataProvider;

    public void create(String ssoToken, CreateGoal createGoal) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        saveBudgetDataProvider.saveOrUpdate((BudgetsGoalEntity.builder()
                .id(UUID.randomUUID())
                .brotherhood(user.getBrotherhood())
                .title(createGoal.name())
                .description(createGoal.getDescription())
                .targetValue(createGoal.getValue())
                .build()));
    }

}
