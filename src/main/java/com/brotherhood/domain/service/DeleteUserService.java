package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.exception.BadRequestException;
import com.brotherhood.model.UserTypeEnum;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteUserService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveUserDataProvider saveUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public void removeFromRep(String ssoToken, UUID id) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        UserEntity user = getUserDataProvider.findById(id);
        if (user.getType().equals(UserTypeEnum.ADMIN)) {
            throw new BadRequestException("Admin can't be deleted");
        }
        user.setBrotherhood(null);
        saveUserDataProvider.saveOrUpdate(user);
    }
}