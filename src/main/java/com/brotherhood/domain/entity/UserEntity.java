package com.brotherhood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    private UUID id;

    @Column(name = "google_id")
    private String googleId;

    private String name;

    private String picture;

    private LocalDate birthdate;

    private String phone;

    private String email;

    private UserTypeEntity type;

    @ManyToOne
    @JoinColumn(name="fk_brotherhood", referencedColumnName = "id", unique = true)
    private BrotherhoodEntity brotherhood;
}
