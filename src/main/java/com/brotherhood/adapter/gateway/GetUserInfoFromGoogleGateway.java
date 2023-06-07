package com.brotherhood.adapter.gateway;

import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.model.UserSSO;
import com.brotherhood.exception.InvalidUserTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import javax.inject.Singleton;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Singleton
public class GetUserInfoFromGoogleGateway implements GetUserInfoFromGoogleDataProvider {
    public static final String PICTURE_KEY = "picture";
    private final GoogleIdTokenVerifier ssoVerify;

    public GetUserInfoFromGoogleGateway(GoogleIdTokenVerifier ssoVerify) {
        this.ssoVerify = ssoVerify;
    }

    @Override
    public UserSSO getUserInfo(String ssoToken) {
        try {
            GoogleIdToken idToken = ssoVerify.verify(ssoToken);
            if (idToken == null) {
                throw new InvalidUserTokenException("Invalid token received from client side!");
            }
            GoogleIdToken.Payload payload = idToken.getPayload();
            return UserSSO.builder()
                    .userId(payload.getSubject())
                    .email(payload.getEmail())
                    .pictureUrl((String) payload.get(PICTURE_KEY))
                    .build();
        } catch (IOException | GeneralSecurityException ex) {
            throw new InvalidUserTokenException(ex.getMessage());
        }
    }
}
