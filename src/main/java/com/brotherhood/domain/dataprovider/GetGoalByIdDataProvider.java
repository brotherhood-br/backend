package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.BudgetsGoalEntity;

import java.util.UUID;

public interface GetGoalByIdDataProvider {
    BudgetsGoalEntity getGoalById(UUID id);
}
