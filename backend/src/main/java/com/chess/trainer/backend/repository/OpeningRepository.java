package com.chess.trainer.backend.repository;

import java.util.UUID;

import com.chess.trainer.backend.model.Opening;

import org.springframework.data.repository.CrudRepository;

public interface OpeningRepository extends CrudRepository<Opening, UUID> {

}
