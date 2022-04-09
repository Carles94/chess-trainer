package com.chess.trainer.domain.mapper;

import com.chess.trainer.domain.model.OpeningDto;
import com.chess.trainer.persistence.entity.OpeningEntity;

import org.mapstruct.Mapper;

@Mapper(uses = LineMapper.class)
public interface OpeningMapper {
    OpeningDto entityToDto(OpeningEntity source);

    OpeningEntity dtoToEntity(OpeningDto destination);
}
