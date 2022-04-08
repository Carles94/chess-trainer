package com.chess.trainer.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chess.trainer.domain.model.MoveDto;
import com.chess.trainer.domain.model.MoveEventDto;
import com.chess.trainer.domain.model.PositionDto;
import com.chess.trainer.persistence.repository.PositionRepository;

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
        PositionDto currentPosition = new PositionDto();
        List<MoveDto> moveList = new ArrayList<>();
        MoveDto otherMove = new MoveDto();
        otherMove.setMoveToSend("e2e4");
        moveList.add(otherMove);
        currentPosition.setMoveList(moveList);
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setMove("d2d4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        PositionDto result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(1, result.getTotalAnswers());
        Assertions.assertEquals(0, result.getCorrectAnswers());
    }

    @Test
    void testUpdatePositionCorrectAnswer() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        PositionDto currentPosition = new PositionDto();
        currentPosition.setTotalAnswers(1);
        List<MoveDto> moveList = new ArrayList<>();
        MoveDto correctMove = new MoveDto();
        correctMove.setMoveToSend("e2e4");
        moveList.add(correctMove);
        currentPosition.setMoveList(moveList);
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setMove("e2e4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        PositionDto result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(2, result.getTotalAnswers());
        Assertions.assertEquals(1, result.getCorrectAnswers());
    }

    @Test
    void testUpdatePositionCorrectAnswer2() {
        // Arrange
        UUID lineUuid = UUID.randomUUID();
        PositionDto currentPosition = new PositionDto();
        currentPosition.setTotalAnswers(2);
        currentPosition.setCorrectAnswers(1);
        List<MoveDto> moveList = new ArrayList<>();
        MoveDto correctMove = new MoveDto();
        correctMove.setMoveToSend("e2e4");
        moveList.add(correctMove);
        currentPosition.setMoveList(moveList);
        MoveEventDto moveEvent = new MoveEventDto();
        moveEvent.setMove("e2e4");
        when(positionRepository.save(currentPosition)).thenAnswer((arguments) -> arguments.getArgument(0));
        // Act
        PositionDto result = positionService.updatePosition(moveEvent, currentPosition, lineUuid);
        // Assert
        verify(positionRepository).save(currentPosition);
        Assertions.assertEquals(3, result.getTotalAnswers());
        Assertions.assertEquals(2, result.getCorrectAnswers());
    }

    @Test
    void testAddMoveToPosition() {
        // Arrange
        PositionDto position = new PositionDto();
        position.setMoveList(new ArrayList<>());
        MoveEventDto moveToAdd = new MoveEventDto();
        moveToAdd.setMove("move");
        moveToAdd.setFen("fen");
        moveToAdd.setMoveToShow("moveToShow");
        when(positionRepository.save(position)).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        PositionDto result = positionService.addMoveToPosition(position, moveToAdd);
        // Assert
        Assertions.assertEquals("fen", result.getMoveList().get(0).getPositionFENAfter());
        Assertions.assertEquals("move", result.getMoveList().get(0).getMoveToSend());
        Assertions.assertEquals("moveToShow", result.getMoveList().get(0).getMoveToShow());
    }

    @Test
    void testDeleteMove() {
        // Arrange
        MoveDto move = new MoveDto();
        move.setMoveToSend("moveToSend");
        PositionDto position = new PositionDto();
        List<MoveDto> moveList = new ArrayList<>();
        moveList.add(move);
        position.setMoveList(moveList);
        when(positionRepository.save(position)).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        PositionDto result = positionService.deleteMove(position, move);
        // Assert
        Assertions.assertTrue(result.getMoveList().isEmpty());
    }

    @Test
    void testCreatePosition() {
        // Arrange
        String fenPosition = "fen";
        UUID lineUuid = UUID.randomUUID();
        when(positionRepository.save(any(PositionDto.class))).thenAnswer(parameter -> parameter.getArgument(0));
        // Act
        PositionDto result = positionService.createPosition(fenPosition, lineUuid);
        // Assert
        verify(positionRepository).save(result);
        Assertions.assertEquals(fenPosition, result.getFenPosition());
        Assertions.assertTrue(result.getMoveList().isEmpty());
        Assertions.assertEquals(lineUuid, result.getLineUuid());
        Assertions.assertEquals(0, result.getCorrectAnswers());
        Assertions.assertEquals(0, result.getTotalAnswers());
    }

}
