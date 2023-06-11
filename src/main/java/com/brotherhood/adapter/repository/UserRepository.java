package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.CreateUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Transactional
public class UserRepository implements CreateUserDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        entityManager.saveOrUpdate(user);
        return user;
    }
}
