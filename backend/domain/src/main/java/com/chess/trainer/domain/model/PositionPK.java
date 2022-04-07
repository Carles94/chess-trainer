package com.chess.trainer.backend.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class PositionPK implements Serializable {
    private String fenPosition;
    private UUID lineUuid;
}