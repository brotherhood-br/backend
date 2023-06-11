package com.brotherhood.adapter.repository;


import com.brotherhood.domain.dataprovider.GetTaskDataProvider;
import com.brotherhood.domain.dataprovider.GetUserDataProvider;
import com.brotherhood.domain.dataprovider.SaveTaskDataProvider;
import com.brotherhood.domain.dataprovider.SaveUserDataProvider;
import com.brotherhood.domain.entity.TaskEntity;
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
public class TaskRepository implements SaveTaskDataProvider {
    @PersistenceContext
    private Session entityManager;

    @Override
    @Transactional
    public TaskEntity saveOrUpdate(TaskEntity task) {
        entityManager.saveOrUpdate(task);
        return task;
    }


}
