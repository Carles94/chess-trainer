package com.chess.trainer.backend.model;

import lombok.Data;

@Data
public class DeleteLineBody {
    private String lineUuid;
    private String openingName;
}
