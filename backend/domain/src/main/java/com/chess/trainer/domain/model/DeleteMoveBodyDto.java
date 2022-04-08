package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class DeleteMoveBodyDto {
    private MoveDto move;
    private PositionDto currentPosition;
}
