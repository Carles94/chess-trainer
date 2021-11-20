package com.chess.trainer.backend.model;

import lombok.Data;

@Data
public class DeleteMoveBody {
    private Move move;
    private Position currentPosition;
    private String lineUuid;
}
