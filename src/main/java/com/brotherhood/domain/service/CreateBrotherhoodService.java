package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.SaveBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.UploadImageDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.model.CreateBrotherhood;
import io.micronaut.core.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateBrotherhoodService {
    @Inject
    private SaveBrotherhoodDataProvider createBrotherhoodDataProvider;

    @Inject
    private UploadImageDataProvider uploadImageDataProvider;

    @Inject
    private CreateUserService createUserService;

    public void create(String ssoToken, CreateBrotherhood createBrotherhood) {
        BrotherhoodEntity brotherhood = BrotherhoodEntity.builder()
                .id(UUID.randomUUID())
                .name(createBrotherhood.name())
                .phone(createBrotherhood.phone())
                .inviteToken(UUID.randomUUID())
                .maxOccupation(createBrotherhood.capacity())
                .description(createBrotherhood.description())
                .characteristics(createBrotherhood.getCharacteristics())
                .build();

        setLogo(createBrotherhood, brotherhood);
        setBanner(createBrotherhood, brotherhood);

        createBrotherhoodDataProvider.save(brotherhood);
        createUserService.createAdminUser(ssoToken, createBrotherhood.admin(), brotherhood);
    }

    private void setBanner(CreateBrotherhood createBrotherhood, BrotherhoodEntity brotherhood) {
        if (StringUtils.isNotEmpty(createBrotherhood.banner())) {
            brotherhood.setBanner(uploadImageDataProvider.uploadImage(createBrotherhood.getBanner()));
        }
    }

    private void setLogo(CreateBrotherhood createBrotherhood, BrotherhoodEntity brotherhood) {
        if (StringUtils.isNotEmpty(createBrotherhood.logo())) {
            brotherhood.setLogo(uploadImageDataProvider.uploadImage(createBrotherhood.logo()));
        }
    }

}
