package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.TaskEntity;

public interface SaveTaskDataProvider {
    TaskEntity saveOrUpdate(TaskEntity task);
}
