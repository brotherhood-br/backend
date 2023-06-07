package com.brotherhood.controller;

import com.brotherhood.api.BrotherhoodApi;
import com.brotherhood.domain.service.CreateBrotherhoodService;
import com.brotherhood.model.Brotherhood;
import com.brotherhood.model.CreateBrotherhood;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BrotherhoodController implements BrotherhoodApi {
    private final CreateBrotherhoodService createBrotherhoodService;

    @Override
    public HttpResponse<Brotherhood> brotherhoodsIdGet(UUID id) {
        return HttpResponse.ok(new Brotherhood().banner("Hello World!"));
    }

    @Override
    public HttpResponse<Object> createBrotherHood(String ssoToken, CreateBrotherhood createBrotherhood) {
        createBrotherhoodService.create(ssoToken, createBrotherhood);
        return HttpResponse.noContent();
    }

}
