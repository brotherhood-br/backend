package com.brotherhood.controller;

import com.brotherhood.api.BrotherhoodApi;
import com.brotherhood.model.Brotherhood;
import com.brotherhood.model.CreateBrotherhood;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BrotherhoodController implements BrotherhoodApi {

    @Override
    public HttpResponse<Brotherhood> brotherhoodsIdGet(UUID id) {
        return HttpResponse.ok(new Brotherhood().banner("Hello World!"));
    }

    @Override
    public HttpResponse<Object> createBrotherHood(CreateBrotherhood createBrotherhood, @Nonnull Optional<String> ssoToken) {
        return null;
    }
}
