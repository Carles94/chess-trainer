package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class CreateLineBody {
    private String lineColor;
    private String lineName;
    private String openingName;
}
