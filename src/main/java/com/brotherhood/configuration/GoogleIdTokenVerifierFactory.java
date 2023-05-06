package com.brotherhood.configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import java.util.Collections;

@Factory
public class GoogleIdTokenVerifierFactory {
    @Bean
    public GoogleIdTokenVerifier getGoogleIdTokenVerifier(final MsEnvironment env) {
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(env.getGoogleClientId()))
                .build();
    }
}
