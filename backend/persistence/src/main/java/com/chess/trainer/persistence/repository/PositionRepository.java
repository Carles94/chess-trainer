package com.chess.trainer.backend.repository;

import com.chess.trainer.backend.model.Position;

import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, String> {

}
