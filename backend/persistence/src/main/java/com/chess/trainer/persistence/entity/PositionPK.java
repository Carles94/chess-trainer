package com.chess.trainer.persistence.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class PositionPK implements Serializable {
    private String fenPosition;
    private UUID lineUuid;
}