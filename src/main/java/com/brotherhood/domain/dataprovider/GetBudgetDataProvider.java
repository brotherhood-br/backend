package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.model.BudgetCompleteCard;

import java.util.List;
import java.util.UUID;

public interface GetBudgetDataProvider {
    List<BudgetCompleteCard> findAllCompleteCards(UUID budgetId);
}
