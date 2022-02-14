package com.chess.trainer.backend.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.backend.model.Move;
import com.chess.trainer.backend.model.MoveEvent;
import com.chess.trainer.backend.model.Position;
import com.chess.trainer.backend.repository.PositionRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {

    // Class to test
    private PositionService positionService;

    // Mocks
    private PositionRepository positionRepository;

    @BeforeEach
    public void init(@Mock PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
        positionService = new PositionService(positionRepository);
    }

    @Test
    void testUpdatePositionFailedAnswer() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        Position currentPosition = new Position();
        List<Move> moveList = new ArrayList<>();
        Move otherMove = new Move();
        otherMove.setMoveToSend("e2e4");
        moveList.add(otherMove);
        currentPosition.setMoveList(moveList);
        MoveEvent moveEvent = new MoveEvent();
        moveEvent.setMove("d2d4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        Position result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(1, result.getTotalAnswers());
        Assertions.assertEquals(0, result.getCorrectAnswers());
    }

    @Test
    void testUpdatePositionCorrectAnswer() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setTotalAnswers(1);
        List<Move> moveList = new ArrayList<>();
        Move correctMove = new Move();
        correctMove.setMoveToSend("e2e4");
        moveList.add(correctMove);
        currentPosition.setMoveList(moveList);
        MoveEvent moveEvent = new MoveEvent();
        moveEvent.setMove("e2e4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        Position result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(2, result.getTotalAnswers());
        Assertions.assertEquals(1, result.getCorrectAnswers());
    }

    @Test
    void testUpdatePositionCorrectAnswer2() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        Position currentPosition = new Position();
        currentPosition.setTotalAnswers(2);
        currentPosition.setCorrectAnswers(1);
        List<Move> moveList = new ArrayList<>();
        Move correctMove = new Move();
        correctMove.setMoveToSend("e2e4");
        moveList.add(correctMove);
        currentPosition.setMoveList(moveList);
        MoveEvent moveEvent = new MoveEvent();
        moveEvent.setMove("e2e4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        Position result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(3, result.getTotalAnswers());
        Assertions.assertEquals(2, result.getCorrectAnswers());
    }

    @Test
    void testAddMoveToPosition() {
        // Arrange
        Position position = new Position();
        position.setMoveList(new ArrayList<>());
        MoveEvent moveToAdd = new MoveEvent();
        moveToAdd.setMove("move");
        moveToAdd.setFen("fen");
        moveToAdd.setMoveToShow("moveToShow");
        when(positionRepository.save(position)).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        Position result = positionService.addMoveToPosition(position, moveToAdd);
        // Assert
        Assertions.assertEquals("fen", result.getMoveList().get(0).getPositionFENAfter());
        Assertions.assertEquals("move", result.getMoveList().get(0).getMoveToSend());
        Assertions.assertEquals("moveToShow", result.getMoveList().get(0).getMoveToShow());
    }

    @Test
    void testDeleteMove() {
        // Arrange
        Move move = new Move();
        move.setMoveToSend("moveToSend");
        Position position = new Position();
        List<Move> moveList = new ArrayList<>();
        moveList.add(move);
        position.setMoveList(moveList);
        when(positionRepository.save(position)).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        Position result = positionService.deleteMove(position, move);
        // Assert
        Assertions.assertTrue(result.getMoveList().isEmpty());
    }

}
