package com.chess.trainer.persistence.repository;

import com.chess.trainer.persistence.entity.Opening;

import org.springframework.data.repository.CrudRepository;

public interface OpeningRepository extends CrudRepository<Opening, String> {

}
