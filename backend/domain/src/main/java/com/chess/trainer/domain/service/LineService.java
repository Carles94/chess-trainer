package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.domain.constant.Constants;
import com.chess.trainer.domain.mapper.LineMapper;
import com.chess.trainer.domain.model.LineDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.entity.LineEntity;
import com.chess.trainer.persistence.repository.LineRepository;
import com.chess.trainer.domain.utils.LineUtils;
import com.chess.trainer.domain.utils.PositionUtils;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class LineService {

    private LineRepository lineRepository;
    private PositionService positionService;
    private LineMapper lineMapper = Mappers.getMapper(LineMapper.class);

    public LineService(LineRepository lineRepository, PositionService positionService) {
        this.lineRepository = lineRepository;
        this.positionService = positionService;
    }

    public PositionDto getPositionFromLineByFen(String fenPosition, UUID lineUuid) {
        LineEntity lineEntity = lineRepository.findById(lineUuid).get();
        LineDto line = lineMapper.entityToDto(lineEntity);
        return LineUtils.getPositionFromLineByFen(fenPosition, line);
    }

    public PositionDto addMove(MoveEventDto moveToAdd, PositionDto currentPosition, UUID lineUuid) {
        LineEntity lineEntity = lineRepository.findById(lineUuid).get();
        LineDto line = lineMapper.entityToDto(lineEntity);
        PositionDto futureCurrentPosition;
        if (LineUtils.canAddMove(moveToAdd.getMove(), currentPosition.getFenPosition(), moveToAdd.getColor(), line)) {
            if (!LineUtils.existsPositionInLine(moveToAdd.getFen(), line)) {
                // Creates position after the move
                futureCurrentPosition = positionService.createPosition(moveToAdd.getFen(), lineUuid);
                // TODO test this
                line.getPositionList().add(futureCurrentPosition);
            } else {
                // Gets position after the move
                futureCurrentPosition = LineUtils.getPositionFromLineByFen(moveToAdd.getFen(), line);
            }

            // Gets position to add the move
            var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
            if (!PositionUtils.existsMove(moveToAdd.getMove(), currentPositionInLine)) {
                positionService.addMoveToPosition(currentPosition, moveToAdd);
            }
            return futureCurrentPosition;
        }
        return null;
    }

    public LineDto createLine(String lineColor, String lineName) {
        LineDto lineToCreate = new LineDto();
        lineToCreate.setColor(lineColor);
        lineToCreate.setName(lineName);
        lineToCreate.setUuid(UUID.randomUUID());
        lineRepository.save(lineMapper.dtoToEntity(lineToCreate));
        // Create initial position
        List<PositionDto> positionList = new ArrayList<>();
        PositionDto initialPosition = positionService.createPosition(Constants.INITIAL_FEN, lineToCreate.getUuid());
        positionList.add(initialPosition);
        lineToCreate.setPositionList(positionList);
        lineRepository.save(lineMapper.dtoToEntity(lineToCreate));
        return lineToCreate;
    }

    public void deleteLine(UUID lineUuid) {
        lineRepository.deleteById(lineUuid);
    }

}
