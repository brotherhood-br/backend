package com.brotherhood.adapter.repository;

import com.brotherhood.domain.dataprovider.GetBrotherhoodById;
import com.brotherhood.domain.dataprovider.GetBrotherhoodByInviteTokenDataProvider;
import com.brotherhood.domain.dataprovider.SaveBrotherhoodDataProvider;
import com.brotherhood.domain.entity.BrotherhoodEntity;
import com.brotherhood.exception.BadRequestException;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.UUID;

@Singleton
public class BrotherhoodRepository implements SaveBrotherhoodDataProvider, GetBrotherhoodByInviteTokenDataProvider, GetBrotherhoodById {

    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public BrotherhoodEntity save(BrotherhoodEntity brotherhood) {
        try {
            entityManager.saveOrUpdate(brotherhood);
        } catch (ConstraintViolationException ex) {
            throw new BadRequestException("Already exists one user with this e-mail!");
        }
        return brotherhood;
    }

    @Override
    @Transactional
    public BrotherhoodEntity findById(UUID id) {
        TypedQuery<BrotherhoodEntity> query = entityManager.createQuery("SELECT b FROM BrotherhoodEntity b WHERE b.id = :id", BrotherhoodEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public BrotherhoodEntity findByInviteToken(UUID token) {
        TypedQuery<BrotherhoodEntity> query = entityManager.createQuery("SELECT b FROM BrotherhoodEntity b WHERE b.inviteToken = :token", BrotherhoodEntity.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }
}
