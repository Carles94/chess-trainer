package com.chess.trainer.backend.repository;

import java.util.UUID;

import com.chess.trainer.backend.model.Line;

import org.springframework.data.repository.CrudRepository;

public interface LineRepository extends CrudRepository<Line, UUID> {

}
