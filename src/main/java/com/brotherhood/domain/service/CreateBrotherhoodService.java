package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.CreateBrotherhoodDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.model.CreateBrotherhood;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateBrotherhoodService {
    @Inject
    private CreateBrotherhoodDataProvider createBrotherhoodDataProvider;

    @Inject
    private CreateUserService createUserService;

    public void create(String ssoToken, CreateBrotherhood createBrotherhood) {
        BrotherhoodEntity brotherhood = BrotherhoodEntity.builder()
                .id(UUID.randomUUID())
                .name(createBrotherhood.name())
                .phone(createBrotherhood.phone())
                .logo(createBrotherhood.logo())
                .inviteToken(UUID.randomUUID())
                .maxOccupation(createBrotherhood.capacity())
                .description(createBrotherhood.description())
                .banner(createBrotherhood.banner())
                .build();
        createBrotherhoodDataProvider.save(brotherhood);
        createUserService.createAdminUser(ssoToken, createBrotherhood.admin(), brotherhood);
    }

}
