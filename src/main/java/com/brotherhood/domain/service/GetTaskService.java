package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.TaskCounterByStatus;
import com.brotherhood.model.Task;
import com.brotherhood.model.TaskCounterCard;
import com.brotherhood.model.TaskPage;
import com.brotherhood.model.TaskStatusEnum;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class GetTaskService {

    @Inject
    private GetUserInfoFromGoogleDataProvider ssoUserDataProvider;

    @Inject
    private GetTasksByBrotherhoodIdDataProvider getTasksByBrotherhoodIdDataProvider;

    @Inject
    private GetTaskCounterDataProvider getTaskCounterDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    @Inject
    private GetTaskDataProvider getTaskDataProvider;

    public Task getTaskById(String ssoToken, UUID taskId) {
        getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        return mapTask(getTaskDataProvider.findById(taskId));
    }

    public TaskPage getTaskPage(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(ssoUserDataProvider.getUserInfo(ssoToken).getUserId());
        List<TaskEntity> tasksByBrotherhoodId = getTasksByBrotherhoodIdDataProvider.getTasksByBrotherhoodAndUser(user.getBrotherhood().getId());
        List<TaskCounterByStatus> counters = getTaskCounterDataProvider.getTaskCounterByBrotherhood(user.getBrotherhood().getId());
        Map<TaskStatusEnum, Integer> counterByStatus = new HashMap<>();
        counters.forEach(c -> counterByStatus.put(c.getStatus(), c.getCount().intValue()));
        return new TaskPage()
                .counterCarousel(getTaskCounterCard(counterByStatus))
                .tasks(getTasks(tasksByBrotherhoodId));
    }

    private List<Task> getTasks(List<TaskEntity> tasksByBrotherhoodId) {
        return tasksByBrotherhoodId.stream()
                .map(this::mapTask)
                .collect(Collectors.toList());
    }

    private static TaskCounterCard getTaskCounterCard(Map<TaskStatusEnum, Integer> counterByStatus) {
        return new TaskCounterCard()
                .late(counterByStatus.get(TaskStatusEnum.LATE) == null ? 0 : counterByStatus.get(TaskStatusEnum.LATE))
                .available(counterByStatus.get(TaskStatusEnum.AVAILABLE) == null ? 0 : counterByStatus.get(TaskStatusEnum.AVAILABLE))
                .finished(counterByStatus.get(TaskStatusEnum.FINISHED) == null ? 0 : counterByStatus.get(TaskStatusEnum.FINISHED));
    }

    public Task mapTask(TaskEntity entity) {
        UserEntity attachedUser = getUserDataProvider.findById(entity.getUser().getId());
        return new Task()
                .id(entity.getId())
                .title(entity.getTitle())
                .attachedUserId(attachedUser.getId())
                .responsibleName(attachedUser.getName())
                .responsibleImg(attachedUser.getPicture())
                .description(entity.getDescription())
                .expiresOn(entity.getExpiresOn())
                .status(entity.getStatus())
                .frequency(entity.getFrequency());

    }

}