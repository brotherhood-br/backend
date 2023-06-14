package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBrotherhoodById;
import com.brotherhood.domain.dataprovider.GetTasksByBrotherhoodId;
import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.model.HomePage;
import com.brotherhood.model.TaskHome;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GetHomePageService {
    @Inject
    private GetUserInfoFromGoogleDataProvider getUserInfoFromGoogleDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    @Inject
    private GetBrotherhoodById getBrotherhoodById;

    @Inject
    private GetTasksByBrotherhoodId getTasksByBrotherhoodId;

    @Transactional
    public HomePage getHomePage(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(getUserInfoFromGoogleDataProvider.getUserInfo(ssoToken).getUserId());
        BrotherhoodEntity brotherhood = getBrotherhoodById.findById(user.getBrotherhood().getId());
        List<TaskEntity> tasks = getTasksByBrotherhoodId.getTasksByBrotherhoodId(brotherhood.getId());
        return new HomePage()
                .brotherhoodLogo(brotherhood.getLogo())
                .brotherhoodBanner(brotherhood.getBanner())
                .userId(user.getId())
                .userName(user.getName())
                .userType(user.getType())
                .tasks(getTasks(tasks));
    }

    private List<TaskHome> getTasks(List<TaskEntity> tasks) {
        return tasks.stream().map(this::toTask)
                .collect(Collectors.toList());
    }

    private TaskHome toTask(TaskEntity entity) {
        return new TaskHome().id(entity.getId())
                .title(entity.getTitle())
                .expireDate(entity.getExpiresOn());
    }
}