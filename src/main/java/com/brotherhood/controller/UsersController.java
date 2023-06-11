package com.brotherhood.controller;

import com.brotherhood.api.UsersApi;
import com.brotherhood.domain.service.CreateUserService;
import com.brotherhood.domain.service.DeleteUserService;
import com.brotherhood.domain.service.GetUserService;
import com.brotherhood.domain.service.UpdateUserService;
import com.brotherhood.model.CreateUser;
import com.brotherhood.model.UpdateUser;
import com.brotherhood.model.UserCard;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import org.hibernate.sql.Update;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Controller
public class UsersController implements UsersApi {
    @Inject
    private CreateUserService createUserService;
    @Inject
    private DeleteUserService deleteUserService;
    @Inject
    private UpdateUserService updateUserService;
    @Inject
    private GetUserService getUserService;

    @Override
    public HttpResponse<Object> createUser(String ssoToken, CreateUser createUser) {
        createUserService.createInvitedUser(ssoToken, createUser);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> deleteUser(String ssoToken, UUID id) {
        deleteUserService.removeFromRep(ssoToken, id);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<List<UserCard>> getUsers(String ssoToken) {
        return HttpResponse.ok(getUserService.getAllSimpleCard(ssoToken));
    }

    @Override
    public HttpResponse<Object> updateUser(String ssoToken, UUID id, UpdateUser updateUser) {
        updateUserService.patchUser(ssoToken, id, updateUser);
        return HttpResponse.noContent();
    }
}
