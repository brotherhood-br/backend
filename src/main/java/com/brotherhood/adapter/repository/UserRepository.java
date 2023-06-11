package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
@Transactional
public class UserRepository implements SaveUserDataProvider, GetUserDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public UserEntity saveOrUpdate(UserEntity user) {
        entityManager.saveOrUpdate(user);
        return user;
    }
    @Override
    public UserEntity findById(UUID id) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
