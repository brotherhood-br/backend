package com.brotherhood.adapter.repository;

import com.brotherhood.domain.dataprovider.SaveAddressDataProvider;
import com.brotherhood.domain.entity.AddressBrotherhoodEntity;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
public class AddressRepository implements SaveAddressDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public AddressBrotherhoodEntity save(AddressBrotherhoodEntity entity) {
        entityManager.saveOrUpdate(entity);
        return entity;
    }
}
