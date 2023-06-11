package com.brotherhood.controller;

import com.brotherhood.api.TasksApi;
import com.brotherhood.domain.service.CreateTaskService;
import com.brotherhood.domain.service.CreateUserService;
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

    @Override
    public HttpResponse<Object> createTask(String ssoToken, CreateTask createTask) {
        createTaskService.createTask(ssoToken, createTask);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> deleteTask(String ssoToken, UUID id) {
        return null;
    }

    @Override
    public HttpResponse<TaskPage> getTaskPage(String ssoToken, @Nonnull Optional<String> taskStatus) {
        return null;
    }

    @Override
    public HttpResponse<Object> patchTask(String ssoToken, UUID id, UpdateTask updateTask) {
        return null;
    }

    @Override
    public HttpResponse<Object> updateTask(String ssoToken, UUID id, CreateTask createTask) {
        return null;
    }
}
