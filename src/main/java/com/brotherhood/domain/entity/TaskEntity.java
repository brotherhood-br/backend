package com.brotherhood.domain.entity;

import com.brotherhood.model.TaskFrequencyEnum;
import com.brotherhood.model.TaskStatusEnum;
import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_task")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskEntity implements Serializable {
    @Id
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime expiresOn;

    private TaskStatusEnum status;

    private TaskFrequencyEnum frequency;

    @ManyToOne
    @JoinColumn(name = "fk_brotherhood", referencedColumnName = "id")
    @LazyInit
    private BrotherhoodEntity brotherhood;

    @ManyToOne
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    @LazyInit
    private UserEntity user;
}
