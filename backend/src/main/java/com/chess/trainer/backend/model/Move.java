package com.chess.trainer.backend.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Move {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
