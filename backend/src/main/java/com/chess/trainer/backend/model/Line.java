package com.chess.trainer.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Line {
    private String name;
    private String color;
    private List<Position> positionList;
}
