package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface GetTasksByBrotherhoodId {
    List<TaskEntity> getTasksByBrotherhoodId(UUID brotherhoodId);
}
