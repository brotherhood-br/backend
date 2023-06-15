package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.model.TaskCounterByStatus;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskRepository implements SaveTaskDataProvider, DeleteTaskDataProvider, GetTaskDataProvider, GetTasksByBrotherhoodIdDataProvider, GetTaskCounterDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public TaskEntity saveOrUpdate(TaskEntity task) {
        entityManager.saveOrUpdate(task);
        return task;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        entityManager.delete(TaskEntity.builder().id(id).build());
    }
    @Override
    @Transactional
    public TaskEntity findById(UUID id) {
        TypedQuery<TaskEntity> query = entityManager.createQuery("SELECT t FROM TaskEntity t WHERE t.id = :id", TaskEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    @Override
    @Transactional
    public List<TaskEntity> getTasksByBrotherhoodId(UUID brotherhoodId) {
        TypedQuery<TaskEntity> query = entityManager.createQuery("SELECT t FROM TaskEntity t WHERE t.brotherhood.id = :id", TaskEntity.class);
        query.setParameter("id", brotherhoodId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<TaskCounterByStatus> getTaskCounterByBrotherhood(UUID brotherhoodId) {
        TypedQuery<TaskCounterByStatus> query = entityManager.createQuery("SELECT new com.brotherhood.domain.model.TaskCounterByStatus(count(t), t.status) FROM TaskEntity t WHERE t.brotherhood.id = :id GROUP BY t.status", TaskCounterByStatus.class);
        query.setParameter("id", brotherhoodId);
        return query.getResultList();
    }
}
