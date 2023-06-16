package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.model.TaskCounterByStatus;

import java.util.List;
import java.util.UUID;

public interface GetTaskCounterDataProvider {
    List<TaskCounterByStatus> getTaskCounterByBrotherhood(UUID brotherhoodId);
}
