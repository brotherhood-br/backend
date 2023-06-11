package com.brotherhood.controller;

import com.brotherhood.api.UsersApi;
import com.brotherhood.domain.service.CreateUserService;
import com.brotherhood.model.CreateUser;
import com.brotherhood.model.UpdateUser;
import com.brotherhood.model.UserCard;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.inject.Inject;
import java.util.UUID;

@Controller
public class UsersController implements UsersApi {
    @Inject
    private CreateUserService createUserService;

    @Override
    public HttpResponse<Object> createUser(String ssoToken, CreateUser createUser) {
        createUserService.createInvitedUser(ssoToken, createUser);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> deleteUser(String ssoToken, UUID id) {
        return null;
    }

    @Override
    public HttpResponse<UserCard> getUsers(String ssoToken) {
        return null;
    }

    @Override
    public HttpResponse<Object> updateUser(String ssoToken, UUID id, UpdateUser updateUser) {
        return null;
    }
}
