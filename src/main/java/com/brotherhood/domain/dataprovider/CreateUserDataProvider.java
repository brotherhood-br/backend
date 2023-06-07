package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.UserEntity;

public interface CreateUserDataProvider {
    UserEntity save(UserEntity user);
}
