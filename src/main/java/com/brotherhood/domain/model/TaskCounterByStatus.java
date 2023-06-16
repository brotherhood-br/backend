package com.brotherhood.domain.model;

import com.brotherhood.model.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskCounterByStatus {
    private Long count;
    private TaskStatusEnum status;
}
