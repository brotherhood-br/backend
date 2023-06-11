package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.UserEntity;

public interface SaveUserDataProvider {
    UserEntity saveOrUpdate(UserEntity user);
}
