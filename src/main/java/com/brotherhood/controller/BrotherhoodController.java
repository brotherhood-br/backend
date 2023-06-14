package com.brotherhood.controller;

import com.brotherhood.api.BrotherhoodApi;
import com.brotherhood.domain.service.CreateBrotherhoodService;
import com.brotherhood.domain.service.GetBrotherhoodAdminPageService;
import com.brotherhood.domain.service.GetBrotherhoodService;
import com.brotherhood.domain.service.UpdateBrotherhoodService;
import com.brotherhood.model.Brotherhood;
import com.brotherhood.model.BrotherhoodAdminPage;
import com.brotherhood.model.CreateBrotherhood;
import com.brotherhood.model.CreateBrotherhoodWithAdmin;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.UUID;

@Controller
public class BrotherhoodController implements BrotherhoodApi {

    @Inject
    private CreateBrotherhoodService createBrotherhoodService;

    @Inject
    private UpdateBrotherhoodService updateBrotherhoodService;

    @Inject
    private GetBrotherhoodAdminPageService getBrotherhoodAdminPageService;

    @Inject
    private GetBrotherhoodService getBrotherhoodService;

    @SneakyThrows
    @Override
    public HttpResponse<Object> createBrotherHood(String ssoToken, CreateBrotherhoodWithAdmin createBrotherhood) {
         createBrotherhoodService.create(ssoToken, createBrotherhood);
        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<BrotherhoodAdminPage> getBrotherhoodAdminPage(String ssoToken) {
        return HttpResponse.ok(getBrotherhoodAdminPageService.getBrotherhoodAdminPage(ssoToken));
    }

    @Override
    public HttpResponse<Brotherhood> getBrotherhoodById(UUID id, Boolean tracking) {
        return HttpResponse.ok(getBrotherhoodService.getBrotherhoodById(id, tracking));
    }

    @Override
    public HttpResponse<Object> updateBrotherHood(String ssoToken, UUID id, CreateBrotherhood createBrotherhood) {
        updateBrotherhoodService.create(ssoToken, id, createBrotherhood);
        return HttpResponse.noContent();
    }
}
