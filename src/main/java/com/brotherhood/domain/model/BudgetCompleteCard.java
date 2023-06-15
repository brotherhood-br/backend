package com.brotherhood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetCompleteCard {
    private UUID id;
    private String title;
    private String description;
    private double targetValue;
    private double currentValue;
}