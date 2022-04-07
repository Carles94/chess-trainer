package com.chess.trainer.backend.model;

import lombok.Data;

@Data
public class PostMoveBody {

    private MoveEvent moveEvent;
    private Position currentPosition;
    private String lineUuid;

}
