package com.chess.trainer.persistence.repository;

import com.chess.trainer.persistence.entity.PositionEntity;

import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<PositionEntity, String> {

}
