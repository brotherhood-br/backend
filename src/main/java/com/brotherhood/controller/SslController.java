package com.brotherhood.controller;

import com.brotherhood.api.DefaultApi;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

@Controller
public class SslController implements DefaultApi {
    @Override
    public HttpResponse<String> wellKnownPkiValidation6D2EDE607FDB5D35C5BBD73AD23F3006TxtGet() {
        return HttpResponse.ok("5B41CF41C72B64950944C0B8CBE0D2A4ADC0AC9C5149415C0730410E5C3D7F52\n" +
                "comodoca.com\n" +
                "7fdb0ff2032ccc5");
    }
}
