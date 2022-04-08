package com.chess.trainer.persistence.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Move {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
