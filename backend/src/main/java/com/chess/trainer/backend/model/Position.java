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
@Entity
@Table(name = "positions")
@IdClass(PositionPK.class)
public class Position {
    @Id
    private String fenPosition;
    @Id
    private UUID lineUuid;
    @ElementCollection
    private List<Move> moveList;
    private int totalAnswers;
    private int correctAnswers;
}
