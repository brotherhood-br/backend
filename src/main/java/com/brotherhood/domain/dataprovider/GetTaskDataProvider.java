package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSimpleCard;
import com.brotherhood.model.Task;

import java.util.List;
import java.util.UUID;

public interface GetTaskDataProvider {

    TaskEntity findById(UUID id);

}
