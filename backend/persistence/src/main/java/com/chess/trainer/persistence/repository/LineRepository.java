package com.chess.trainer.persistence.repository;

import java.util.UUID;

import com.chess.trainer.persistence.entity.LineEntity;

import org.springframework.data.repository.CrudRepository;

public interface LineRepository extends CrudRepository<LineEntity, UUID> {

}
