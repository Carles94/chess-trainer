package com.chess.trainer.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "openings")
public class OpeningEntity {
    @Id
    private String name;
    @OneToMany(targetEntity = LineEntity.class, fetch = FetchType.EAGER)
    private List<LineEntity> lineList;
    private String color;
}
