package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.BudgetsGoalEntity;

public interface SaveBudgetDataProvider {
    BudgetsGoalEntity saveOrUpdate(BudgetsGoalEntity budgetsGoal);
}
