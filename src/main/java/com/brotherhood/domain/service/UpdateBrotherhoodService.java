package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBrotherhoodByIdDataProvider;
import com.brotherhood.domain.dataprovider.GetUserInfoFromGoogleDataProvider;
import com.brotherhood.domain.dataprovider.SaveBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.UploadImageDataProvider;
import com.brotherhood.domain.entity.AddressBrotherhoodEntity;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.model.CreateBrotherhood;
import io.micronaut.core.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UpdateBrotherhoodService {
    @Inject
    private SaveBrotherhoodDataProvider saveBrotherhoodDataProvider;

    @Inject
    private UploadImageDataProvider uploadImageDataProvider;

    @Inject
    private GetBrotherhoodByIdDataProvider getBrotherhoodById;
    @Inject
    private GetUserInfoFromGoogleDataProvider getUserInfoFromGoogleDataProvider;

    public void create(String ssoToken, UUID id, CreateBrotherhood createBrotherhood) {
        getUserInfoFromGoogleDataProvider.getUserInfo(ssoToken);
        BrotherhoodEntity brotherhood = getBrotherhoodById.findById(id);
        brotherhood.setName(createBrotherhood.name());
        brotherhood.setPhone(createBrotherhood.phone());
        brotherhood.setMaxOccupation(createBrotherhood.capacity());
        brotherhood.setDescription(createBrotherhood.description());
        brotherhood.setCharacteristics(createBrotherhood.characteristics());
        brotherhood.setType(createBrotherhood.getType());

        AddressBrotherhoodEntity address = brotherhood.getAddress();
        address.setCountry(createBrotherhood.address().country());
        address.setCity(createBrotherhood.address().getCity());
        address.setStreet(createBrotherhood.address().getStreet());
        address.setNumber(createBrotherhood.address().getNumber());
        address.setZipCode(createBrotherhood.address().getZipCode());
        address.setState(createBrotherhood.address().getState());

        setLogo(createBrotherhood, brotherhood);
        setBanner(createBrotherhood, brotherhood);
        saveBrotherhoodDataProvider.save(brotherhood);
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
