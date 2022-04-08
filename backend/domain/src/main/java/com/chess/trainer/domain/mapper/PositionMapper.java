package com.chess.trainer.domain.mapper;

import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.entity.PositionEntity;

public interface PositionMapper {
    PositionDto entityToDto(PositionEntity source);

    PositionEntity dtoToEntity(PositionDto destination);
}
