package com.brotherhood.controller;

import com.brotherhood.api.BudgetsApi;
import com.brotherhood.domain.service.ContributeToGoalService;
import com.brotherhood.domain.service.CreateBudgetService;
import com.brotherhood.domain.service.GetBudgetsService;
import com.brotherhood.domain.service.GetGoalService;
import com.brotherhood.model.BudgetsPage;
import com.brotherhood.model.CreateGoal;
import com.brotherhood.model.Goal;
import com.brotherhood.model.InlineObject;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.inject.Inject;
import java.util.UUID;

@Controller
public class BudgetController implements BudgetsApi  {

    @Inject
    private GetBudgetsService getBudgetsService;

    @Inject
    private CreateBudgetService createBudgetService;

    @Inject
    private ContributeToGoalService contributeToGoalService;

    @Inject
    private GetGoalService getGoalService;

    @Override
    public HttpResponse<Object> contribute(String ssoToken, UUID goalId, InlineObject value) {
        contributeToGoalService.contribute(ssoToken, goalId, value.getValue());
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> createGoal(String ssoToken, CreateGoal createGoal) {
        createBudgetService.create(ssoToken, createGoal);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> deleteGoal(String ssoToken, UUID id) {
        return null;
    }

    @Override
    public HttpResponse<BudgetsPage> getBudgetsPage(String ssoToken) {
        return HttpResponse.ok(getBudgetsService.getAllCompleteCards(ssoToken));
    }

    @Override
    public HttpResponse<Goal> getGoalById(String ssoToken, UUID id) {
        return HttpResponse.ok(getGoalService.getGoalById(ssoToken, id));
    }

    @Override
    public HttpResponse<Object> updateGoal(String ssoToken, UUID id, CreateGoal createGoal) {
        return null;
    }
}