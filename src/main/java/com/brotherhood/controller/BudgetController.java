package com.brotherhood.controller;

import com.brotherhood.api.BudgetsApi;
import com.brotherhood.domain.service.GetBudgetsService;
import com.brotherhood.domain.service.UpdateTaskService;
import com.brotherhood.model.BudgetsPage;
import com.brotherhood.model.CreateGoal;
import com.brotherhood.model.Goal;
import com.brotherhood.model.InlineObject;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.Data;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;



@Controller
public class BudgetController implements BudgetsApi  {

    @Inject
    private GetBudgetsService getBudgetsService;

    @Override
    public HttpResponse<Object> contribute(String ssoToken, UUID id, InlineObject inlineObject) {
        return null;
    }

    @Override
    public HttpResponse<Object> createGoal(String ssoToken, CreateGoal createGoal) {
        return null;
    }

    @Override
    public HttpResponse<Object> deleteGoal(String ssoToken, UUID id) {
        return null;
    }

    @Override
    public HttpResponse<BudgetsPage> getBudgetsPage(String ssoToken) {
        getBudgetsService.getAllCompleteCards(ssoToken);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Goal> getGoalById(String ssoToken, UUID id) {
        return null;
    }

    @Override
    public HttpResponse<Object> updateGoal(String ssoToken, UUID id, CreateGoal createGoal) {
        return null;
    }
}