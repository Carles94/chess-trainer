package com.chess.trainer.persistence.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MoveEntity {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
