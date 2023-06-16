package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface GetTasksByBrotherhoodIdDataProvider {
    List<TaskEntity> getTasksByBrotherhoodAndUser(UUID brotherhoodId);
    List<TaskEntity> getTasksByBrotherhoodAndUser(UUID brotherhoodId, UUID userId);
}
