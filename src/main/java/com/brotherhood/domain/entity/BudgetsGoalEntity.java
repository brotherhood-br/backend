package com.brotherhood.domain.entity;

import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_budget_goal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BudgetsGoalEntity implements Serializable {
    @Id
    private UUID id;

    private String title;

    private String description;

    private double targetValue;

    @ManyToOne
    @JoinColumn(name = "fk_brotherhood", referencedColumnName = "id")
    @LazyInit
    private BrotherhoodEntity brotherhood;

}

