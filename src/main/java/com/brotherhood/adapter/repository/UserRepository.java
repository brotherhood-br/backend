package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.CreateUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
@RequiredArgsConstructor
public class UserRepository implements CreateUserDataProvider {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        entityManager.persist(user);
        return user;
    }
}
