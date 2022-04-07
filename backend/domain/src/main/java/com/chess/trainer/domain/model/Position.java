package com.chess.trainer.backend.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
public class Position {
    private String fenPosition;
    private UUID lineUuid;
    private List<Move> moveList;
    private int totalAnswers;
    private int correctAnswers;
}
