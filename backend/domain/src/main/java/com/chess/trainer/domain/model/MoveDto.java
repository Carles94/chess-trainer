package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class MoveDto {
    private String moveToShow;
    private String moveToSend;
    private String positionFENAfter;
}
