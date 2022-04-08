package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class CreateLineBodyDto {
    private String lineColor;
    private String lineName;
    private String openingName;
}
