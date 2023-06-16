package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSimpleCard;
import com.brotherhood.model.User;
import com.brotherhood.model.UserCard;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class GetUserService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public List<UserCard> getAllSimpleCard(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        return getUserDataProvider.findAllSimpleCards(user.getBrotherhood().getId())
                .stream()
                .map(GetUserService::getUserCard)
                .collect(Collectors.toList());
    }
    public User getUser(String ssoToken, UUID userId) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        return new User().id(userId)
                .name(user.getName())
                .picture(user.getPicture())
                .birthdate(user.getBirthdate())
                .phone(user.getPhone())
                .email(user.getEmail())
                .type(user.getType());
    }

    private static UserCard getUserCard(UserSimpleCard e) {
        return new UserCard()
                .id(e.getId())
                .name(e.getName())
                .image(e.getImage())
                .type(e.getType());
    }
}