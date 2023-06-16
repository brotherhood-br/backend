package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBudgetGoalByIdDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.model.BudgetCompleteCard;
import com.brotherhood.model.Goal;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class GetGoalService {
    @Inject
    private GetBudgetGoalByIdDataProvider getGoalByIdDataProvider;

    @Inject
    private GetUserInfoFromGoogleDataProvider getSSODataProvider;

    public Goal getGoalById(String ssoToken, UUID goalId) {
        getSSODataProvider.getUserInfo(ssoToken);
        BudgetCompleteCard budgetCompleteCard = getGoalByIdDataProvider.getBudgetCompleteCardById(goalId);
        return new Goal().id(budgetCompleteCard.getId())
                .title(budgetCompleteCard.getTitle())
                .description(budgetCompleteCard.getDescription())
                .targetValue(budgetCompleteCard.getTargetValue())
                .currentValue(budgetCompleteCard.getCurrentValue());
    }
}
