package com.brotherhood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "tb_address_brotherhood")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBrotherhoodEntity {

    @Id
    private UUID id;
    private String country;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private String state;

}
