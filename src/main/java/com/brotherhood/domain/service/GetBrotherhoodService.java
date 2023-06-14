package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.GetBrotherhoodByIdDataProvider;
import com.brotherhood.domain.dataprovider.GetOccupationByBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.SaveBrotherhoodViewDataProvider;
import com.brotherhood.domain.entity.AddressBrotherhoodEntity;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.BrotherhoodViewsEntity;
import com.brotherhood.model.Address;
import com.brotherhood.model.Brotherhood;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.UUID;

@Singleton
public class GetBrotherhoodService {
    @Inject
    private GetOccupationByBrotherhoodDataProvider getOccupationByBrotherhoodDataProvider;

    @Inject
    private GetBrotherhoodByIdDataProvider getBrotherhoodById;

    @Inject
    private SaveBrotherhoodViewDataProvider saveBrotherhoodViewDataProvider;

    public Brotherhood getBrotherhoodById(UUID id, boolean tracking) {
        BrotherhoodEntity brotherhoodEntity = getBrotherhoodById.findById(id);
        Brotherhood brotherhood = new Brotherhood()
                .brotherhoodToken(brotherhoodEntity.getInviteToken())
                .name(brotherhoodEntity.getName())
                .description(brotherhoodEntity.getDescription())
                .phone(brotherhoodEntity.getPhone())
                .logo(brotherhoodEntity.getLogo())
                .type(brotherhoodEntity.getType())
                .banner(brotherhoodEntity.getBanner())
                .capacity(brotherhoodEntity.getMaxOccupation())
                .membersCount(getOccupationByBrotherhoodDataProvider.getOccupation(brotherhoodEntity.getId()))
                .address(getAddress(brotherhoodEntity.getAddress()));
        if (tracking) {
            incrementBrotherhoodViewCount(brotherhoodEntity);
        }
        return brotherhood;
    }

    private void incrementBrotherhoodViewCount(BrotherhoodEntity entity) {
        BrotherhoodViewsEntity brotherhoodView = BrotherhoodViewsEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDate.now())
                .brotherhood(entity)
                .build();
        saveBrotherhoodViewDataProvider.save(brotherhoodView);
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
