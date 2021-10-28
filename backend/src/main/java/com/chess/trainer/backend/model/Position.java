package com.chess.trainer.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Position {
    private String FENPosition;
    private String previousFENPosition;
    private List<Move> moveList;
}
