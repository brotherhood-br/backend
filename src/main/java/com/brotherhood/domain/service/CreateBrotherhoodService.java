package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.SaveAddressDataProvider;
import com.brotherhood.domain.dataprovider.SaveBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.UploadImageDataProvider;
import com.brotherhood.domain.entity.AddressBrotherhoodEntity;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.model.Address;
import com.brotherhood.model.CreateBrotherhoodWithAdmin;
import io.micronaut.core.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class CreateBrotherhoodService {
    @Inject
    private SaveBrotherhoodDataProvider createBrotherhoodDataProvider;

    @Inject
    private SaveAddressDataProvider saveAddressDataProvider;

    @Inject
    private UploadImageDataProvider uploadImageDataProvider;

    @Inject
    private CreateUserService createUserService;

    public void create(String ssoToken, CreateBrotherhoodWithAdmin createBrotherhood) {
        AddressBrotherhoodEntity address = getAddress(createBrotherhood.address());
        BrotherhoodEntity brotherhood = BrotherhoodEntity.builder()
                .id(UUID.randomUUID())
                .name(createBrotherhood.name())
                .phone(createBrotherhood.phone())
                .inviteToken(UUID.randomUUID())
                .maxOccupation(createBrotherhood.capacity())
                .description(createBrotherhood.description())
                .type(createBrotherhood.type())
                .characteristics(createBrotherhood.getCharacteristics())
                .address(address)
                .build();

        setLogo(createBrotherhood, brotherhood);
        setBanner(createBrotherhood, brotherhood);

        saveAddressDataProvider.save(address);
        createBrotherhoodDataProvider.save(brotherhood);
        createUserService.createAdminUser(ssoToken, createBrotherhood.admin(), brotherhood);
    }

    private void setBanner(CreateBrotherhoodWithAdmin createBrotherhood, BrotherhoodEntity brotherhood) {
        if (StringUtils.isNotEmpty(createBrotherhood.banner())) {
            brotherhood.setBanner(uploadImageDataProvider.uploadImage(createBrotherhood.getBanner()));
        }
    }

    private void setLogo(CreateBrotherhoodWithAdmin createBrotherhood, BrotherhoodEntity brotherhood) {
        if (StringUtils.isNotEmpty(createBrotherhood.logo())) {
            brotherhood.setLogo(uploadImageDataProvider.uploadImage(createBrotherhood.logo()));
        }
    }

    private AddressBrotherhoodEntity getAddress(Address address) {
        return AddressBrotherhoodEntity.builder()
                .id(UUID.randomUUID())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .number(address.getNumber())
                .zipCode(address.getNumber())
                .state(address.getState())
                .build();
    }

}
