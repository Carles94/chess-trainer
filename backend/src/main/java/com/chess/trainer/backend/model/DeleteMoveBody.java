package com.chess.trainer.backend.model;

import java.util.UUID;

import lombok.Data;

@Data
public class DeleteMoveBody {
    private Move move;
    private Position currentPosition;
    private UUID lineUuid;
}
