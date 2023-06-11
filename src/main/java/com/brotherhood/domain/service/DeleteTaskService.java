package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.DeleteTaskDataProvider;
import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.entity.UserTypeEntity;
import com.brotherhood.exception.BadRequestException;
import com.brotherhood.model.Task;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteTaskService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private DeleteTaskDataProvider deleteTaskDataProvider;

    public void deleteTask(String ssoToken, UUID id) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        deleteTaskDataProvider.deleteById(id);
    }
}