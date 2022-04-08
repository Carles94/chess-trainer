package com.chess.trainer.domain.model;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Line {
    private UUID uuid;
    private String name;
    private String color;
    private List<Position> positionList;
}
