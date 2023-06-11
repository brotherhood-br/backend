package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.entity.UserTypeEntity;
import com.brotherhood.domain.model.UserSSO;
import com.brotherhood.model.CreateTask;
import com.brotherhood.model.CreateUser;
import com.brotherhood.model.UpdateTask;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateTaskService {
    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveTaskDataProvider saveTaskDataProvider;
    @Inject
    private GetUserDataProvider getUserDataProvider;

    public void createTask(String ssoToken, CreateTask createTask) {
        TaskEntity task = getTaskEntity(ssoToken, createTask);
        saveTaskDataProvider.saveOrUpdate(task);
    }

    private TaskEntity getTaskEntity(String ssoToken, CreateTask createTask) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        UserEntity user = getUserDataProvider.findById(createTask.attachedUserId());
        return TaskEntity.builder()
                .id(UUID.randomUUID())
                .title(createTask.title())
                .description(createTask.description())
                .expiresOn(createTask.expiresOn())
                .frequency(createTask.frequency())
                .status(UpdateTask.Status.AVAILABLE)
                .user(user)
                .brotherhood(user.getBrotherhood())
                .build();
    }

}
