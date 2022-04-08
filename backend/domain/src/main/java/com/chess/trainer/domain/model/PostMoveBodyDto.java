package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class PostMoveBodyDto {

    private MoveEventDto moveEvent;
    private PositionDto currentPosition;
    private String lineUuid;

}
