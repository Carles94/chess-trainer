package com.chess.trainer.backend.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Position {
    private String FenPosition;
    private String previousFenPosition;
    @ElementCollection
    private List<Move> moveList;
}
