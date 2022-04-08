package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class DeleteMoveBody {
    private Move move;
    private Position currentPosition;
}
