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
@Table(name = "openings")
public class Opening {
    @Id
    private UUID uuid;
    private String name;
    @OneToMany(targetEntity = Line.class, fetch = FetchType.EAGER)
    private List<Line> lineList;
    private String color;
}
