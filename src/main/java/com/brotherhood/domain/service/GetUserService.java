package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.model.UserSimpleCard;
import com.brotherhood.model.UserCard;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GetUserService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public List<UserCard> getAllSimpleCard(String ssoToken) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        return getUserDataProvider.findAllSimpleCards()
                .stream()
                .map(GetUserService::getUserCard)
                .collect(Collectors.toList());
    }

    private static UserCard getUserCard(UserSimpleCard e) {
        return new UserCard()
                .id(e.getId())
                .name(e.getName())
                .image(e.getImage());
    }
}