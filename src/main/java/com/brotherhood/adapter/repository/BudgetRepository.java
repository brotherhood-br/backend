package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.GetBudgetDataProvider;
import com.brotherhood.domain.dataprovider.GetTotalValueByBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.SaveBudgetDataProvider;
import com.brotherhood.domain.entity.BudgetsGoalEntity;
import com.brotherhood.domain.model.BudgetCompleteCard;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
public class BudgetRepository implements GetBudgetDataProvider, SaveBudgetDataProvider, GetTotalValueByBrotherhoodDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public List<BudgetCompleteCard> findAllCompleteCards(UUID brotherhoodId) {
        String queryString = "SELECT new com.brotherhood.domain.model.BudgetCompleteCard(goal.id, goal.title, goal.description, goal.targetValue, COALESCE(SUM(contribution.contributedValue), 0)) FROM BudgetsGoalEntity goal " +
                " LEFT JOIN GoalContributionEntity contribution ON contribution.budgetsGoal.id = goal.id " +
                " WHERE goal.brotherhood.id = :brotherhoodId" +
                " GROUP BY goal.id, goal.title, goal.description, goal.targetValue";
        TypedQuery<BudgetCompleteCard> query = entityManager.createQuery(queryString, BudgetCompleteCard.class);
        query.setParameter("brotherhoodId", brotherhoodId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Double getTotalValueByBrotherhood(UUID brotherhoodId) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT COALESCE(SUM(c.contributedValue), 0) FROM GoalContributionEntity c WHERE c.budgetsGoal.id = :brotherhoodId", Double.class);
        query.setParameter("brotherhoodId", brotherhoodId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public BudgetsGoalEntity saveOrUpdate(BudgetsGoalEntity budgetsGoal) {
        entityManager.saveOrUpdate(budgetsGoal);
        return budgetsGoal;
    }

}
