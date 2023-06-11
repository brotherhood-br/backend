package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.*;
import com.brotherhood.domain.entity.TaskEntity;
import com.brotherhood.domain.entity.UserEntity;
import com.brotherhood.domain.model.UserSimpleCard;
import com.brotherhood.model.Task;
import org.hibernate.Session;
import org.hibernate.boot.model.source.spi.TableSource;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskRepository implements SaveTaskDataProvider, DeleteTaskDataProvider {
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


}
