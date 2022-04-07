package com.chess.trainer.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
public class Opening {
    private String name;
    private List<Line> lineList;
    private String color;
}
