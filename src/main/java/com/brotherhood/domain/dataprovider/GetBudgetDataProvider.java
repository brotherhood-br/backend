package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.model.BudgetCompleteCard;
import com.brotherhood.model.Goal;

import java.util.List;
import java.util.UUID;

public interface GetBudgetDataProvider {
    List<Goal> findAllCompleteCards(UUID budgetId);

}
