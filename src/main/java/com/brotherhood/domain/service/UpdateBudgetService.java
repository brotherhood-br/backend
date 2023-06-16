package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetGoalByIdDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveBudgetDataProvider;
import com.brotherhood.domain.entity.BudgetsGoalEntity;
import com.brotherhood.model.CreateGoal;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UpdateBudgetService {
    @Inject
    private GetGoalByIdDataProvider getGoalByIdDataProvider;
    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveBudgetDataProvider saveBudgetDataProvider;

    public void updateBudget(String ssoToken, UUID id, CreateGoal goal) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        BudgetsGoalEntity goalEntity = getGoalByIdDataProvider.getGoalById(id);
        goalEntity.setTitle(goal.getName());
        goalEntity.setDescription(goal.getDescription());
        goalEntity.setTargetValue(goal.getValue());
        saveBudgetDataProvider.saveOrUpdate(goalEntity);
    }
}
