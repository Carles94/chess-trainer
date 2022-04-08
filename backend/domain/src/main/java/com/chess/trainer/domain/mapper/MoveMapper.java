package com.chess.trainer.domain.mapper;

import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.persistence.entity.MoveEntity;

import org.mapstruct.Mapper;

@Mapper
public interface MoveMapper {
    MoveDto entityToDto(MoveEntity source);

    MoveEntity dtoToEntity(MoveDto destination);
}