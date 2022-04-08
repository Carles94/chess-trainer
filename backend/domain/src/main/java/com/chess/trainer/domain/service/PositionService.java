package com.chess.trainer.domain.service;

import java.util.ArrayList;
import java.util.UUID;

import com.chess.trainer.domain.model.Move;
import com.chess.trainer.domain.model.MoveEvent;
import com.chess.trainer.domain.model.Position;
import com.chess.trainer.persistence.repository.PositionRepository;
import com.chess.trainer.domain.utils.PositionUtils;

import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position updatePosition(MoveEvent moveEvent, Position currentPosition, UUID uuidLine) {
        currentPosition.setTotalAnswers(currentPosition.getTotalAnswers() + 1);
        if (currentPosition.getMoveList().stream()
                .anyMatch((move) -> move.getMoveToSend().equals(moveEvent.getMove()))) {
            currentPosition.setCorrectAnswers(currentPosition.getCorrectAnswers() + 1);
        }
        Position result = positionRepository.save(currentPosition);
        return result;
    }

    public Position addMoveToPosition(Position position, MoveEvent moveToAdd) {
        Move currentMove = new Move();
        currentMove.setMoveToSend(moveToAdd.getMove());
        currentMove.setPositionFENAfter(moveToAdd.getFen());
        currentMove.setMoveToShow(moveToAdd.getMoveToShow());
        position.getMoveList().add(currentMove);
        return positionRepository.save(position);
    }

    public Position deleteMove(Position position, Move move) {
        Position currentPositionInLine = PositionUtils.deleteMove(move, position);
        return positionRepository.save(currentPositionInLine);
    }

    public Position createPosition(String fenPosition, UUID lineUuid) {
        Position positionToCreate = new Position();
        positionToCreate.setFenPosition(fenPosition);
        positionToCreate.setLineUuid(lineUuid);
        positionToCreate.setMoveList(new ArrayList<>());
        return positionRepository.save(positionToCreate);
    }

}
