package com.brotherhood.domain.entity;

import com.brotherhood.model.UserTypeEnum;
import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity implements Serializable {
    @Id
    private UUID id;

    @Column(name = "google_id")
    private String googleId;

    private String name;

    private String picture;

    private LocalDate birthdate;

    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    private UserTypeEnum type;

    @ManyToOne
    @JoinColumn(name="fk_brotherhood", referencedColumnName = "id")
    @LazyInit
    private BrotherhoodEntity brotherhood;
}
