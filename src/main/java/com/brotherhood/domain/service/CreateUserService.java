package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.CreateUserDataProvider;
import com.brotherhood.domain.dataprovider.GetBrotherhoodByInviteTokenDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSSO;
import com.brotherhood.model.CreateUser;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class CreateUserService {
    private final GetUserInfoFromGoogleDataProvider ssoUserDataProvider;
    private final GetBrotherhoodByInviteTokenDataProvider getBrotherhoodByInviteTokenDataProvider;
    private final CreateUserDataProvider createUserDataProvider;

    public void createInvitedUser(String ssoToken, CreateUser createUser) {
        UserEntity user = getUserEntity(ssoToken, createUser);
        BrotherhoodEntity brotherhood = getBrotherhoodByInviteTokenDataProvider.find(createUser.brotherhoodToken());
        user.setBrotherhood(brotherhood);
        createUserDataProvider.save(user);
    }

    public void createAdminUser(String ssoToken, CreateUser createUser, BrotherhoodEntity brotherhood) {
        UserEntity user = getUserEntity(ssoToken, createUser);
        user.setBrotherhood(brotherhood);
        createUserDataProvider.save(user);
    }

    private UserEntity getUserEntity(String ssoToken, CreateUser createUser) {
        UserSSO userSSO = ssoUserDataProvider.getUserInfo(ssoToken);

        return UserEntity.builder()
                .picture(userSSO.getPictureUrl())
                .id(UUID.randomUUID())
                .email(userSSO.getEmail())
                .googleId(userSSO.getUserId()).phone(createUser.getPhone())
                .name(createUser.getName())
                .birthdate(createUser.getBirthDate())
                .build();
    }

}
