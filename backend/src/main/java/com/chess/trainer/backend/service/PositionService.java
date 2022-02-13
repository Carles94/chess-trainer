package com.chess.trainer.backend.service;

import java.util.UUID;

import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.PositionRepository;

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

}
