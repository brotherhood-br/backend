package com.brotherhood.adapter.repository;

import com.brotherhood.domain.dataprovider.CreateBrotherhoodDataProvider;
import com.brotherhood.domain.dataprovider.GetBrotherhoodByInviteTokenDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class BrotherhoodRepository implements CreateBrotherhoodDataProvider, GetBrotherhoodByInviteTokenDataProvider {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public BrotherhoodEntity save(BrotherhoodEntity brotherhood) {
        entityManager.persist(brotherhood);
        return brotherhood;
    }

    @Override
    public BrotherhoodEntity find(UUID token) {
        TypedQuery<BrotherhoodEntity> query = entityManager.createQuery("SELECT b FROM BrotherhoodEntity b WHERE b.inviteToken = :token", BrotherhoodEntity.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }
}
