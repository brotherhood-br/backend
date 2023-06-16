package com.brotherhood.domain.model;

import com.brotherhood.model.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSimpleCard {
    private UUID id;
    private String name;
    private String image;
    private UserTypeEnum type;
}