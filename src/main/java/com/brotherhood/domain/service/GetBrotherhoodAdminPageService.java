package com.brotherhood.domain.service;

import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.model.BrotherhoodAdminPage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class GetBrotherhoodAdminPageService {
    @Inject
    private GetUserInfoFromGoogleDataProvider getUserInfoFromGoogleDataProvider;

    @Inject
    private GetUserDataProvider getUserDataProvider;

    @Inject
    private GetBrotherhoodByIdDataProvider getBrotherhoodById;

    @Inject
    private GetOccupationByBrotherhoodDataProvider getOccupationByBrotherhoodDataProvider;

    @Inject
    private GetBrotherhoodViewCountBeforeDateDataProvider getBrotherhoodViewCountBeforeDateDataProvider;

    public BrotherhoodAdminPage getBrotherhoodAdminPage(String ssoToken) {
        UserEntity user = getUserDataProvider.findByToken(getUserInfoFromGoogleDataProvider.getUserInfo(ssoToken).getUserId());
        BrotherhoodEntity brotherhood = getBrotherhoodById.findById(user.getBrotherhood().getId());
        return new BrotherhoodAdminPage()
                .viewCount(getBrotherhoodViewCountBeforeDateDataProvider.getViewCount(LocalDate.now().minusDays(7), user.getBrotherhood().getId()))
                .brotherhoodId(brotherhood.getId())
                .brotherhoodInviteToken(brotherhood.getInviteToken())
                .occupation(getOccupationByBrotherhoodDataProvider.getOccupation(brotherhood.getId()))
                .capacity(brotherhood.getMaxOccupation());
    }
}
