package com.chess.trainer.backend.model;

import lombok.Data;

@Data
public class MoveEvent {
    private boolean check;
    private boolean checkmate;
    private String color;
    private String fen;
    private String move;
    private String piece;
    private boolean stalemate;
    private boolean capture;
}
