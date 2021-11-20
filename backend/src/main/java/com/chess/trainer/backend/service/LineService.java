package com.chess.trainer.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

import com.chess.trainer.backend.constant.FenConstant;
import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.PositionRepository;
import com.chess.trainer.backend.utils.LineUtils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    private LineRepository lineRepository;
    private PositionRepository positionRepository;

    public LineService(LineRepository lineRepository, PositionRepository positionRepository) {
        this.lineRepository = lineRepository;
        this.positionRepository = positionRepository;
        // TODO always init a new Line with this position
        Line line = new Line();
        line.setUuid(UUID.fromString("53f93e7c-4d34-433a-b0d2-824f134a9829"));
        Position position = new Position();
        position.setFenPosition(FenConstant.INITIAL_FEN);
        position.setMoveList(new ArrayList<>());
        positionRepository.save(position);
        line.setPositionList(Collections.singletonList(position));
        lineRepository.save(line);
    }

    public Position getPositionFromLineByFen(String fenPosition, UUID lineUuid) {
        Line line = lineRepository.findById(lineUuid).get();
        return LineUtils.getPositionFromLineByFen(fenPosition, line);
    }

    public Position addMove(MoveEvent moveEvent, Position currentPosition, UUID uuid) {
        // Creates a new position and add to line
        var futureCurrentPosition = new Position();
        futureCurrentPosition.setFenPosition(moveEvent.getFen());
        futureCurrentPosition.setMoveList(new ArrayList<>());
        futureCurrentPosition.setPreviousFenPosition(currentPosition.getFenPosition());
        Line line = lineRepository.findById(uuid).get();
        line.getPositionList().add(futureCurrentPosition);
        positionRepository.save(futureCurrentPosition);

        // Creates a new move and adds to position
        var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
        Move currentMove = new Move();
        currentMove.setMoveToSend(moveEvent.getMove());
        currentMove.setPositionFENAfter(moveEvent.getFen());
        currentMove.setMoveToShow(moveEvent.getMoveToShow());
        currentPositionInLine.getMoveList().add(currentMove);
        positionRepository.save(currentPositionInLine);
        // TODO see if necessary
        lineRepository.save(line);
        return futureCurrentPosition;
    }

    public Position deleteMove(Move moveToDelete, Position currentPosition, UUID uuid) {
        Line line = lineRepository.findById(uuid).get();
        var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
        List<Move> currentPositionMoveList = currentPositionInLine.getMoveList().stream()
                .filter((move) -> !move.getMoveToSend().equals(moveToDelete.getMoveToSend()))
                .collect(Collectors.toList());
        currentPositionInLine.setMoveList(currentPositionMoveList);
        positionRepository.save(currentPositionInLine);
        // TODO see if necessary
        lineRepository.save(line);
        return currentPositionInLine;
    }
}
