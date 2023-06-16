package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveTaskDataProvider;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.model.CreateTask;
import com.brotherhood.model.TaskStatusEnum;

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
        TaskEntity task = TaskEntity.builder()
                .id(UUID.randomUUID())
                .title(createTask.title())
                .description(createTask.description())
                .expiresOn(createTask.expiresOn())
                .frequency(createTask.frequency())
                .status(TaskStatusEnum.AVAILABLE)
                .brotherhood(user.getBrotherhood())
                .build();
        if (createTask.attachedUserId() != null) {
            task.setUser(getUserDataProvider.findById(createTask.getAttachedUserId()));
        }
        return task;
    }
}