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
@Table(name = "tb_goal_contribution")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GoalContributionEntity implements Serializable {
    @Id
    private UUID id;

    private double contributedValue;

    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_contributor", referencedColumnName = "id")
    @LazyInit
    private UserEntity contributor;

    @ManyToOne
    @JoinColumn(name = "fk_budgets_goal", referencedColumnName = "id")
    @LazyInit
    private BudgetsGoalEntity budgetsGoal;
}

