package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSimpleCard;

import java.util.List;
import java.util.UUID;

public interface GetUserDataProvider {
    UserEntity findById(UUID id);
    UserEntity findByToken(String token);

    List<UserSimpleCard> findAllSimpleCards();

}
