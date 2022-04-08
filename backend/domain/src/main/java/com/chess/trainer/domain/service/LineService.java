package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.domain.constant.Constants;
import com.chess.trainer.domain.model.Line;
import com.chess.trainer.domain.model.MoveEvent;
import com.chess.trainer.domain.model.Position;
import com.chess.trainer.persistence.repository.LineRepository;
import com.chess.trainer.domain.utils.LineUtils;
import com.chess.trainer.domain.utils.PositionUtils;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    private LineRepository lineRepository;
    private PositionService positionService;

    public LineService(LineRepository lineRepository, PositionService positionService) {
        this.lineRepository = lineRepository;
        this.positionService = positionService;
    }

    public Position getPositionFromLineByFen(String fenPosition, UUID lineUuid) {
        Line line = lineRepository.findById(lineUuid).get();
        return LineUtils.getPositionFromLineByFen(fenPosition, line);
    }

    public Position addMove(MoveEvent moveToAdd, Position currentPosition, UUID lineUuid) {
        // Creates a new position and add to line
        Line line = lineRepository.findById(lineUuid).get();
        Position futureCurrentPosition;
        if (LineUtils.canAddMove(moveToAdd.getMove(), currentPosition.getFenPosition(), moveToAdd.getColor(), line)) {
            if (!LineUtils.existsPositionInLine(moveToAdd.getFen(), line)) {
                futureCurrentPosition = positionService.createPosition(moveToAdd.getFen(), lineUuid);
                line.getPositionList().add(futureCurrentPosition);
            } else {
                futureCurrentPosition = LineUtils.getPositionFromLineByFen(moveToAdd.getFen(), line);
            }

            // Creates a new move and adds to position
            var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
            if (!PositionUtils.existsMove(moveToAdd.getMove(), currentPositionInLine)) {
                positionService.addMoveToPosition(currentPosition, moveToAdd);
            }
            return futureCurrentPosition;
        }
        return null;
    }

    public Line createLine(String lineColor, String lineName) {
        Line lineToCreate = new Line();
        lineToCreate.setColor(lineColor);
        lineToCreate.setName(lineName);
        lineToCreate.setUuid(UUID.randomUUID());
        lineRepository.save(lineToCreate);
        // Create initial position
        List<Position> positionList = new ArrayList<>();
        Position initialPosition = positionService.createPosition(Constants.INITIAL_FEN, lineToCreate.getUuid());
        positionList.add(initialPosition);
        lineToCreate.setPositionList(positionList);
        lineRepository.save(lineToCreate);
        return lineToCreate;
    }

    public void deleteLine(UUID lineUuid) {
        lineRepository.deleteById(lineUuid);
    }

}
