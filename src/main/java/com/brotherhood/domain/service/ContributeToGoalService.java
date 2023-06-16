package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.GoalContributionEntity;
import com.brotherhood.domain.entity.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class ContributeToGoalService {

    @Inject
    private GetGoalByIdDataProvider getGoalByIdDataProvider;
    @Inject
    private GetUserDataProvider getUserDataProvider;

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveContributionDataProvider saveContributionDataProvider;

    public void contribute(String ssoToken, UUID goalId, Double value) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        GoalContributionEntity contribution = GoalContributionEntity.builder()
                .id(UUID.randomUUID())
                .contributedValue(value)
                .contributor(user)
                .budgetsGoal(getGoalByIdDataProvider.getGoalById(goalId))
                .build();
        saveContributionDataProvider.saveOrUpdate(contribution);
    }

}
