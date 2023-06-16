package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.exception.BadRequestException;
import com.brotherhood.model.UserTypeEnum;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteBudgetService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private DeleteBudgetDataProvider deleteBudgetDataProvider;
    @Inject
    private GetUserDataProvider getUserDataProvider;

    public void deleteBudgetGoal(String ssoToken, UUID id) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        if (!UserTypeEnum.ADMIN.equals(user.getType())) {
            throw new BadRequestException("Only admin can delete a goal");
        }
        deleteBudgetDataProvider.deleteById(id);

    }
}