package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.GetOccupationByBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSimpleCard;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
public class UserRepository implements SaveUserDataProvider, GetUserDataProvider, GetOccupationByBrotherhoodDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public UserEntity saveOrUpdate(UserEntity user) {
        entityManager.saveOrUpdate(user);
        return user;
    }
    @Override
    @Transactional
    public UserEntity findById(UUID id) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    @Override
    @Transactional
    public List<UserSimpleCard> findAllSimpleCards() {
        TypedQuery<UserSimpleCard> query = entityManager.createQuery("SELECT new com.brotherhood.domain.model.UserSimpleCard(u.id, u.name, u.picture) FROM UserEntity u", UserSimpleCard.class);
        return query.getResultList();
    }
    @Override
    @Transactional
    public UserEntity findByToken(String token) {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.googleId = :token", UserEntity.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public Integer getOccupation(UUID brotherhoodId) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT count(u) FROM UserEntity u WHERE u.brotherhood.id = :id", Long.class);
        query.setParameter("id", brotherhoodId);
        return query.getSingleResult().intValue();
    }
}
