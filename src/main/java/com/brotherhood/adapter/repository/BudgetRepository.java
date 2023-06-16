package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.BudgetsGoalEntity;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.model.BudgetCompleteCard;
import com.brotherhood.model.Goal;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
public class BudgetRepository implements GetBudgetDataProvider, SaveBudgetDataProvider, DeleteBudgetDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public List<Goal> findAllCompleteCards(UUID budgetId) {
        TypedQuery<Goal> query = entityManager.createQuery("SELECT new com.brotherhood.domain.model.Goal(b.id, b.title, b.description, b.targetValue, sum(g.contributedValue)) " +
                                                              "FROM BudgetsGoalEntity b inner join GoalContributionEntity g on g.budgetsGoal.id = b.id " +
                                                              "WHERE g.brotherhood.id = :id group by b.id, b.title, b.description, b.targetValue", Goal.class);
        query.setParameter("id", budgetId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public BudgetsGoalEntity saveOrUpdate(BudgetsGoalEntity budgetsGoal) {
        entityManager.saveOrUpdate(budgetsGoal);
        return budgetsGoal;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        entityManager.createNativeQuery("DELETE FROM tb_goal_contribution where fk_budgets_goal = :id ; DELETE FROM tb_budget_goal where id = :id " ).setParameter("id", id).executeUpdate();
    }
}
