package com.chess.trainer.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Position {
    private String FenPosition;
    private String previousFenPosition;
    private List<Move> moveList;
}
