package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveTaskDataProvider;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.model.CreateTask;

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
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        if (createTask.attachedUserId() != null) {
            return TaskEntity.builder()
                    .id(UUID.randomUUID())
                    .title(createTask.title())
                    .description(createTask.description())
                    .expiresOn(createTask.expiresOn())
                    .frequency(createTask.frequency())
                    .user(getUserDataProvider.findById(createTask.getAttachedUserId()))
                    .brotherhood(user.getBrotherhood())
                    .build();
        }
        return TaskEntity.builder()
                .id(UUID.randomUUID())
                .title(createTask.title())
                .description(createTask.description())
                .expiresOn(createTask.expiresOn())
                .frequency(createTask.frequency())
                .brotherhood(user.getBrotherhood())
                .build();
    }
}