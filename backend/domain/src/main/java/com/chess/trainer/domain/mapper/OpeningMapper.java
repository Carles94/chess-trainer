package com.chess.trainer.domain.mapper;

import com.chess.trainer.domain.model.OpeningDto;
import com.chess.trainer.persistence.entity.OpeningEntity;

public interface OpeningMapper {
    OpeningDto entityToDto(OpeningEntity source);

    OpeningEntity dtoToEntity(OpeningDto destination);
}
