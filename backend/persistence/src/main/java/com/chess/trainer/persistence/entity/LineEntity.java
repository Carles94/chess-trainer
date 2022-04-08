package com.chess.trainer.persistence.entity;

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
public class LineEntity {
    @Id
    private UUID uuid;
    private String name;
    private String color;
    @OneToMany(targetEntity = PositionEntity.class, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PositionEntity> positionList;
}
