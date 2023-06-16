package com.brotherhood.controller;

import com.brotherhood.api.TasksApi;
import com.brotherhood.domain.service.CreateTaskService;
import com.brotherhood.domain.service.DeleteTaskService;
import com.brotherhood.domain.service.GetTaskService;
import com.brotherhood.domain.service.UpdateTaskService;
import com.brotherhood.model.CreateTask;
import com.brotherhood.model.Task;
import com.brotherhood.model.TaskPage;
import com.brotherhood.model.UpdateTask;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.inject.Inject;
import java.util.UUID;

@Controller
public class TaskController implements TasksApi {

    @Inject
    private CreateTaskService createTaskService;

    @Inject
    private DeleteTaskService deleteTaskService;

    @Inject
    private UpdateTaskService updateTaskService;

    @Inject
    private GetTaskService getTaskService;

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
    public HttpResponse<Task> getTaskById(String ssoToken, UUID id) {
        return HttpResponse.ok(getTaskService.getTaskById(ssoToken, id));
    }

    @Override
    public HttpResponse<TaskPage> getTaskPage(String ssoToken) {
        return HttpResponse.ok(getTaskService.getTaskPage(ssoToken));
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