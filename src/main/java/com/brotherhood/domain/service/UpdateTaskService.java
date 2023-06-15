package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.exception.BadRequestException;
import com.brotherhood.model.CreateTask;
import com.brotherhood.model.UpdateTask;
import com.brotherhood.model.UpdateUser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import java.util.UUID;

@Singleton
public class UpdateTaskService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private SaveTaskDataProvider saveTaskDataProvider;

    @Inject
    private GetTaskDataProvider getTaskDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    public void updateTask(String ssoToken, UUID id, CreateTask createTask) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        TaskEntity task = getTaskDataProvider.findById(id);
        task.setTitle(createTask.title());
        task.setDescription(createTask.description());
        task.setExpiresOn(createTask.getExpiresOn());
        task.setFrequency(createTask.getFrequency());
        try {
            task.setUser(getUserDataProvider.findById(createTask.getAttachedUserId()));
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        saveTaskDataProvider.saveOrUpdate(task);
    }

    public void patchTask(String ssoToken, UUID id, boolean unbindUser, UpdateTask updateTask) {
        ssoUserDataProvider.getUserInfo(ssoToken);
        TaskEntity task = getTaskDataProvider.findById(id);
        if (updateTask.status() == null && updateTask.attachedUserId() == null && unbindUser == false) {
            throw new BadRequestException("Nothing to update");
        }
        if (updateTask.status() != null) {
            task.setStatus(updateTask.getStatus());
        }
        if (updateTask.attachedUserId() != null) {
            task.setUser(getUserDataProvider.findById(updateTask.getAttachedUserId()));
        }
        if (unbindUser) {
            task.setUser(null);
        }
        saveTaskDataProvider.saveOrUpdate(task);
    }

}
