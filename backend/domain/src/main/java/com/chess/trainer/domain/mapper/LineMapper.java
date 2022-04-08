package com.chess.trainer.domain.mapper;

import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.persistence.entity.LineEntity;

public interface LineMapper {
    LineDto entityToDto(LineEntity source);

    LineEntity dtoToEntity(LineDto destination);
}
