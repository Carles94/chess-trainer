package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class Move {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
