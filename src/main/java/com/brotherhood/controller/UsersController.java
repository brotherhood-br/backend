package com.brotherhood.controller;

import com.brotherhood.api.UsersApi;
import com.brotherhood.domain.service.CreateUserService;
import com.brotherhood.model.CreateUser;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final CreateUserService createUserService;

    @Override
    public HttpResponse<Object> createUser(String ssoToken, CreateUser createUser) {
        createUserService.createInvitedUser(ssoToken, createUser);
        return HttpResponse.noContent();
    }
}
