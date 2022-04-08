package com.chess.trainer.domain.model;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class LineDto {
    private UUID uuid;
    private String name;
    private String color;
    private List<PositionDto> positionList;
}
