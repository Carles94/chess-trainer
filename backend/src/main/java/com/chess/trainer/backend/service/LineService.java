package com.chess.trainer.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import com.chess.trainer.backend.constant.Constants;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.PositionRepository;
import com.chess.trainer.backend.utils.LineUtils;
import com.chess.trainer.backend.utils.PositionUtils;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    private LineRepository lineRepository;
    private PositionRepository positionRepository;

    public LineService(LineRepository lineRepository, PositionRepository positionRepository) {
        this.lineRepository = lineRepository;
        this.positionRepository = positionRepository;
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
                futureCurrentPosition = new Position();
                futureCurrentPosition.setFenPosition(moveToAdd.getFen());
                futureCurrentPosition.setLineUuid(lineUuid);
                futureCurrentPosition.setMoveList(new ArrayList<>());
                line.getPositionList().add(futureCurrentPosition);
                positionRepository.save(futureCurrentPosition);
            } else {
                futureCurrentPosition = LineUtils.getPositionFromLineByFen(moveToAdd.getFen(), line);
            }

            // Creates a new move and adds to position
            var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
            if (!PositionUtils.existsMove(moveToAdd.getMove(), currentPositionInLine)) {
                Move currentMove = new Move();
                currentMove.setMoveToSend(moveToAdd.getMove());
                currentMove.setPositionFENAfter(moveToAdd.getFen());
                currentMove.setMoveToShow(moveToAdd.getMoveToShow());
                currentPositionInLine.getMoveList().add(currentMove);
                positionRepository.save(currentPositionInLine);
                // TODO see if necessary
                lineRepository.save(line);
            }
            return futureCurrentPosition;
        }
        return null;
    }

    public Position deleteMove(Move moveToDelete, Position currentPosition, UUID uuid) {
        Line line = lineRepository.findById(uuid).get();
        Position currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
        currentPositionInLine = PositionUtils.deleteMove(moveToDelete, currentPositionInLine);
        positionRepository.save(currentPositionInLine);
        // TODO see if necessary
        lineRepository.save(line);
        return currentPositionInLine;
    }
}
