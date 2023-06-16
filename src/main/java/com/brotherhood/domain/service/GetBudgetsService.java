package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBudgetDataProvider;
import com.brotherhood.domain.dataprovider.GetTotalValueByBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.BudgetCompleteCard;
import com.brotherhood.model.BudgetsPage;
import com.brotherhood.model.Goal;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GetBudgetsService {

    @Inject
    private GetBudgetDataProvider getBudgetDataProvider;

    @Inject
    private GetTotalValueByBrotherhoodDataProvider getTotalValueByBrotherhoodDataProvider;

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public BudgetsPage getAllCompleteCards(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        Double total = getTotalValueByBrotherhoodDataProvider.getTotalValueByBrotherhood(user.getBrotherhood().getId());
        List<BudgetCompleteCard> cards = getBudgetDataProvider.findAllCompleteCards(user.getBrotherhood().getId());
        return new BudgetsPage()
                .totalValue(total)
                .goals(getGoals(cards));
    }

    private List<Goal> getGoals(List<BudgetCompleteCard> cards) {
        return cards.stream().map(this::getGoal).collect(Collectors.toList());
    }

    private Goal getGoal(BudgetCompleteCard budgetCompleteCard) {
        return new Goal().id(budgetCompleteCard.getId())
                .title(budgetCompleteCard.getTitle())
                .description(budgetCompleteCard.getDescription())
                .currentValue(budgetCompleteCard.getCurrentValue())
                .targetValue(budgetCompleteCard.getTargetValue());
    }

}
