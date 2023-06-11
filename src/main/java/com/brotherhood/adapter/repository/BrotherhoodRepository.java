package com.brotherhood.adapter.repository;

import com.brotherhood.domain.dataprovider.GetBrotherhoodByInviteTokenDataProvider;
import com.brotherhood.domain.dataprovider.SaveBrotherhoodDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
public class BrotherhoodRepository implements SaveBrotherhoodDataProvider, GetBrotherhoodByInviteTokenDataProvider {

    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public BrotherhoodEntity save(BrotherhoodEntity brotherhood) {
        entityManager.saveOrUpdate(brotherhood);
        return brotherhood;
    }
    @Override
    public BrotherhoodEntity findByInviteToken(UUID token) {
        TypedQuery<BrotherhoodEntity> query = entityManager.createQuery("SELECT b FROM BrotherhoodEntity b WHERE b.inviteToken = :token", BrotherhoodEntity.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }
}
