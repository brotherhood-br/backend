package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.exception.BadRequestException;
import com.brotherhood.model.UpdateUser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UpdateUserService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveUserDataProvider saveUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public void patchUser(String ssoToken, UUID id, UpdateUser updateUser) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        UserEntity user = getUserDataProvider.findById(id);
        if (updateUser.phone() == null && updateUser.birthdate() == null) {
            throw new BadRequestException("Nothing to update");
        }
        if (updateUser.birthdate() != null) {
            user.setBirthdate(updateUser.birthdate());
        }
        if (updateUser.phone() != null) {
            user.setPhone(updateUser.phone());
        }
        saveUserDataProvider.saveOrUpdate(user);
    }
}
