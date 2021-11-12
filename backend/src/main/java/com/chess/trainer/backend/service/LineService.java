package com.chess.trainer.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import com.chess.trainer.backend.model.Line;
import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.LineRepository;
import com.chess.trainer.backend.repository.PositionRepository;
import com.chess.trainer.backend.utils.LineUtils;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    private LineRepository lineRepository;
    private PositionRepository positionRepository;

    public LineService(LineRepository lineRepository, PositionRepository positionRepository) {
        this.lineRepository = lineRepository;
        this.positionRepository = positionRepository;
        // TODO always init a new Line with this position
        Line line = new Line();
        Position position = new Position();
        position.setFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
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
        var futureCurrentPosition = new Position();
        futureCurrentPosition.setFenPosition(moveEvent.getFen());
        futureCurrentPosition.setMoveList(new ArrayList<>());
        futureCurrentPosition.setPreviousFenPosition(currentPosition.getFenPosition());
        Line line = lineRepository.findById(uuid).get();
        line.getPositionList().add(futureCurrentPosition);
        var currentPositionInLine = LineUtils.getPositionFromLineByFen(currentPosition.getFenPosition(), line);
        Move currentMove = new Move();
        currentMove.setMoveToSend(moveEvent.getMove());
        currentMove.setPositionFENAfter(moveEvent.getFen());
        currentPositionInLine.getMoveList().add(currentMove);
        return futureCurrentPosition;
    }
}
