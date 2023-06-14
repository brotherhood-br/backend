package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBrotherhoodById;
import com.brotherhood.domain.dataprovider.GetOccupationByBrotherhoodDataProvider;
import com.brotherhood.domain.entity.AddressBrotherhoodEntity;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.model.Address;
import com.brotherhood.model.Brotherhood;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class GetBrotherhoodService {
    @Inject
    private GetOccupationByBrotherhoodDataProvider getOccupationByBrotherhoodDataProvider;

    @Inject
    private GetBrotherhoodById getBrotherhoodById;

    public Brotherhood getBrotherhoodById(UUID id) {
        BrotherhoodEntity brotherhood = getBrotherhoodById.findById(id);
        return new Brotherhood()
                .brotherhoodToken(brotherhood.getInviteToken())
                .name(brotherhood.getName())
                .description(brotherhood.getDescription())
                .phone(brotherhood.getPhone())
                .logo(brotherhood.getLogo())
                .type(brotherhood.getType())
                .banner(brotherhood.getBanner())
                .capacity(brotherhood.getMaxOccupation())
                .membersCount(getOccupationByBrotherhoodDataProvider.getOccupation(brotherhood.getId()))
                .address(getAddress(brotherhood.getAddress()));
    }

    private Address getAddress(AddressBrotherhoodEntity address) {
        return new Address()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .number(address.getNumber())
                .zipCode(address.getNumber())
                .state(address.getState());
    }

}
