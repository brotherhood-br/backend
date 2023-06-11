package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.BrotherhoodEntity;

import java.util.UUID;

public interface GetBrotherhoodByInviteTokenDataProvider {
    BrotherhoodEntity findByInviteToken(UUID token);
}
