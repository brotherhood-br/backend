package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.GoalContributionEntity;

public interface SaveContributionDataProvider {
    GoalContributionEntity saveOrUpdate(GoalContributionEntity entity);
}
