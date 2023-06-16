package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.*;
import com.brotherhood.domain.model.BudgetCompleteCard;
import com.brotherhood.model.Address;
import com.brotherhood.model.Brotherhood;
import com.brotherhood.model.BudgetsPage;
import com.brotherhood.model.Goal;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class GetBudgetsService {

    @Inject
    private GetBudgetDataProvider getBudgetDataProvider;

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public BudgetsPage getAllCompleteCards(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        return new BudgetsPage().goals(getBudgetDataProvider.findAllCompleteCards(user.getBrotherhood().getId()));
    }


}
