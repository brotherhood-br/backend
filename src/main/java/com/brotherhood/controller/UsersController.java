package com.brotherhood.controller;

import com.brotherhood.api.UsersApi;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.model.CreateUser;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final GetUserInfoFromGoogleDataProvider dataProvider;

    @Override
    public HttpResponse<Object> createUser(String ssoToken, CreateUser createUser) {
        System.out.println(dataProvider.getUserInfo(ssoToken).getUserId());
        return HttpResponse.noContent();
    }
}
