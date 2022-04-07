package com.chess.trainer.backend.model;

import lombok.Data;

@Data
public class Move {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
