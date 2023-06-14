package com.brotherhood.controller;

import com.brotherhood.api.TasksApi;
import com.brotherhood.domain.service.CreateTaskService;
import com.brotherhood.domain.service.CreateUserService;
import com.brotherhood.domain.service.DeleteTaskService;
import com.brotherhood.domain.service.UpdateTaskService;
import com.brotherhood.model.CreateTask;
import com.brotherhood.model.TaskPage;
import com.brotherhood.model.UpdateTask;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TaskController implements TasksApi {

    @Inject
    private CreateTaskService createTaskService;

    @Inject
    private DeleteTaskService deleteTaskService;

    @Inject
    private UpdateTaskService updateTaskService;

    @Override
    public HttpResponse<Object> createTask(String ssoToken, CreateTask createTask) {
        createTaskService.createTask(ssoToken, createTask);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> deleteTask(String ssoToken, UUID id) {
        deleteTaskService.deleteTask(ssoToken, id);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<TaskPage> getTaskPage(String ssoToken, @Nonnull Optional<String> taskStatus) {
        return null;
    }

    @Override
    public HttpResponse<Object> patchTask(String ssoToken, UUID id, Boolean unbindUser, UpdateTask updateTask) {
        updateTaskService.patchTask(ssoToken, id, unbindUser, updateTask);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> updateTask(String ssoToken, UUID id, CreateTask createTask) {
        updateTaskService.updateTask(ssoToken, id, createTask);
        return HttpResponse.noContent();
    }
}