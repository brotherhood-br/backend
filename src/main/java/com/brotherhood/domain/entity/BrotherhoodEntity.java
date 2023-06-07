package com.brotherhood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_brotherhood")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrotherhoodEntity {
    @Id
    private UUID id;

    @Column(name = "max_occupation")
    private int maxOccupation;

    private UUID inviteToken;

    private String logo;

    private String banner;

    private String name;

    private String description;

    private String phone;

}
