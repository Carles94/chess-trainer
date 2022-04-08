package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.UUID;

import com.chess.trainer.domain.mapper.PositionMapper;
import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.repository.PositionRepository;
import com.chess.trainer.domain.utils.PositionUtils;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private PositionRepository positionRepository;
    private PositionMapper positionMapper = Mappers.getMapper(PositionMapper.class);

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public PositionDto updatePosition(MoveEventDto moveEvent, PositionDto currentPosition, UUID uuidLine) {
        currentPosition.setTotalAnswers(currentPosition.getTotalAnswers() + 1);
        if (currentPosition.getMoveList().stream()
                .anyMatch((move) -> move.getMoveToSend().equals(moveEvent.getMove()))) {
            currentPosition.setCorrectAnswers(currentPosition.getCorrectAnswers() + 1);
        }
        return this.savePosition(currentPosition);
    }

    public PositionDto addMoveToPosition(PositionDto position, MoveEventDto moveToAdd) {
        MoveDto currentMove = new MoveDto();
        currentMove.setMoveToSend(moveToAdd.getMove());
        currentMove.setPositionFENAfter(moveToAdd.getFen());
        currentMove.setMoveToShow(moveToAdd.getMoveToShow());
        position.getMoveList().add(currentMove);
        return this.savePosition(position);
    }

    public PositionDto deleteMove(PositionDto position, MoveDto move) {
        PositionDto currentPositionInLine = PositionUtils.deleteMove(move, position);
        return this.savePosition(currentPositionInLine);
    }

    public PositionDto createPosition(String fenPosition, UUID lineUuid) {
        PositionDto positionToCreate = new PositionDto();
        positionToCreate.setFenPosition(fenPosition);
        positionToCreate.setLineUuid(lineUuid);
        positionToCreate.setMoveList(new ArrayList<>());
        return this.savePosition(positionToCreate);
    }

    private PositionDto savePosition(PositionDto positionDto) {
        return positionMapper.entityToDto(positionRepository.save(positionMapper.dtoToEntity(positionDto)));
    }

}
