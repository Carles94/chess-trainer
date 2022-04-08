package com.chess.trainer.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Opening {
    private String name;
    private List<Line> lineList;
    private String color;
}
