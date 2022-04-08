package com.chess.trainer.persistence.entity;

import lombok.Data;

@Data
public class PostMoveBody {

    private MoveEvent moveEvent;
    private Position currentPosition;
    private String lineUuid;

}
