package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class DeleteLineBodyDto {
    private String lineUuid;
    private String openingName;
}
