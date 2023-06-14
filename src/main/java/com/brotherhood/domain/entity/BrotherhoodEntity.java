package com.brotherhood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_brotherhood")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrotherhoodEntity implements Serializable {
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

    @ElementCollection
    @CollectionTable(name="brotherhood_characteristics", joinColumns=@JoinColumn(name="fk_brotherhood"))
    @Column(name="characteristic")
    private List<String> characteristics;

    @OneToMany
    @JoinColumn(name="fk_brotherhood", referencedColumnName = "id")
    private List<BrotherhoodViewsEntity> views;

}
