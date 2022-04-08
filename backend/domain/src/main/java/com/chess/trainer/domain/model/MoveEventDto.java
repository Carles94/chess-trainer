package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class MoveEventDto {
    private boolean check;
    private boolean checkmate;
    private String color;
    private String fen;
    private String move;
    private String piece;
    private boolean stalemate;
    private boolean capture;
    private String moveToShow;
}
