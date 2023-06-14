package com.brotherhood.adapter.repository;

import com.brotherhood.domain.dataprovider.GetBrotherhoodViewCountBeforeDateDataProvider;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Singleton
public class BrotherhoodViewRepository implements GetBrotherhoodViewCountBeforeDateDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public Integer getViewCount(LocalDate date, UUID brotherhoodId) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT count(v) FROM BrotherhoodViewsEntity v WHERE v.brotherhood.id = :id and v.date <= :date", Long.class);
        query.setParameter("id", brotherhoodId);
        query.setParameter("date", date);
        return query.getSingleResult().intValue();
    }
}
