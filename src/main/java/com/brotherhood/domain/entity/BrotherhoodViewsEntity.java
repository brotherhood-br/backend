package com.brotherhood.domain.entity;

import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_brotherhood_views")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrotherhoodViewsEntity {
    @Id
    private UUID id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "fk_brotherhood", referencedColumnName = "id")
    @LazyInit
    private BrotherhoodEntity brotherhood;
}
