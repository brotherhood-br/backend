package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.model.BudgetCompleteCard;

import java.util.UUID;

public interface GetBudgetGoalByIdDataProvider {
    BudgetCompleteCard getBudgetCompleteCardById(UUID id);
}
