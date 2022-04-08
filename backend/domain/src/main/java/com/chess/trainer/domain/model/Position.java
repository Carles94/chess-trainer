package com.chess.trainer.domain.model;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Position {
    private String fenPosition;
    private UUID lineUuid;
    private List<Move> moveList;
    private int totalAnswers;
    private int correctAnswers;
}
