package com.brotherhood.controller;

import com.brotherhood.api.HomeApi;
import com.brotherhood.domain.service.GetHomePageService;
import com.brotherhood.model.HomePage;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.inject.Inject;

@Controller
public class HomeController implements HomeApi {

    @Inject
    private GetHomePageService getHomePageService;

    @Override
    public HttpResponse<HomePage> getHome(String ssoToken) {
        return HttpResponse.ok(getHomePageService.getHomePage(ssoToken));
    }
}
