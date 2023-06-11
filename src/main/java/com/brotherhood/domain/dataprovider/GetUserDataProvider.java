package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.UserEntity;

import java.util.UUID;

public interface GetUserDataProvider {
    UserEntity findById(UUID id);

}
