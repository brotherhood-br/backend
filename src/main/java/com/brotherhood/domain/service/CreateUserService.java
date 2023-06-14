package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBrotherhoodByInviteTokenDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSSO;
import com.brotherhood.model.CreateUser;
import com.brotherhood.model.HomePage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateUserService {
    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetBrotherhoodByInviteTokenDataProvider getBrotherhoodByInviteTokenDataProvider;

    @Inject
    private SaveUserDataProvider saveUserDataProvider;

    public void createInvitedUser(String ssoToken, CreateUser createUser) {
        UserEntity user = getUserEntity(ssoToken, createUser);
        BrotherhoodEntity brotherhood = getBrotherhoodByInviteTokenDataProvider.findByInviteToken(createUser.brotherhoodToken());
        user.setBrotherhood(brotherhood);
        user.setType(HomePage.UserType.RESIDENT);
        saveUserDataProvider.saveOrUpdate(user);
    }

    public void createAdminUser(String ssoToken, CreateUser createUser, BrotherhoodEntity brotherhood) {
        UserEntity user = getUserEntity(ssoToken, createUser);
        user.setBrotherhood(brotherhood);
        user.setType(HomePage.UserType.ADMIN);
        saveUserDataProvider.saveOrUpdate(user);
    }

    private UserEntity getUserEntity(String ssoToken, CreateUser createUser) {
        UserSSO userSSO = ssoUserDataProvider.getUserInfo(ssoToken);

        return UserEntity.builder()
                .picture(userSSO.getPictureUrl())
                .id(UUID.randomUUID())
                .email(userSSO.getEmail())
                .googleId(userSSO.getUserId())
                .phone(createUser.getPhone())
                .name(createUser.getName())
                .birthdate(createUser.getBirthDate())
                .build();
    }

}
