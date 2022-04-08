package com.chess.trainer.domain.model;

import lombok.Data;

@Data
public class DeleteLineBody {
    private String lineUuid;
    private String openingName;
}
