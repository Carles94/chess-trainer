package com.chess.trainer.backend.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "positions")
public class Position {
    @Id
    private String FenPosition;
    private String previousFenPosition;
    @ElementCollection
    private List<Move> moveList;
}
