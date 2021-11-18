package com.chess.trainer.backend.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lines")
public class Line {
    @Id
    private UUID uuid;
    private String name;
    private String color;
    @OneToMany(targetEntity = Position.class, fetch = FetchType.EAGER)
    private List<Position> positionList;
}
