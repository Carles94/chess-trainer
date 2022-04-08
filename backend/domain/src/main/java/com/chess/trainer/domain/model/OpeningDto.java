package com.chess.trainer.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class OpeningDto {
    private String name;
    private List<LineDto> lineList;
    private String color;
}
